//ExternalInterface
import axios, { AxiosError, AxiosResponse } from "axios";
import { toast } from "react-toastify";
import { ILogin, IRegistration, IUser } from "../model/auth.model";

const GATEWAY = "http://localhost:8010";

const GATEWAY_SERVICE_ROUTE = "";

const API_VERSION = "/api/v1";

axios.defaults.baseURL = `${GATEWAY}${GATEWAY_SERVICE_ROUTE}${API_VERSION}`;

axios.interceptors.request.use(
  (config) => {
    console.log("[OUTBOUND] __API (CON) " + config.method + " : " + config.url);

    config.headers["Content-Type"] = "application/json";

    if (config.url !== "/auth/login" && config.url !="/auth/registration") {
      const token = sessionStorage.getItem("TOKEN");
      if (token) {
        config.headers["Authorization"] = `Bearer ${token}`;
      }
    }

    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

axios.interceptors.response.use(
  async (response) => {
    responseAnalysis(response);

    // // console.log("[INBOUND] __API (INV) RESPONSE", response.data);
    // // console.log(response);
    //toast.success("Success");
    return response;
  },
  (error: AxiosError) => {
    const { status, statusText } = error.response!;
    console.log("-------------------------------------------");
    console.error(
      "[INBOUND] __API (INV) ERROR :: " + status + " :: " + statusText
    );
    console.log("_ EI error");
    console.error(error);
    console.log("_ EI error response");
    console.error(error.response);
    console.log("-------------------------------------------");
    return Promise.reject(error);
  }
);

const responseBody = <T>(response: AxiosResponse<T>) => response.data;

function responseAnalysis(response: AxiosResponse<any, any>) {
  // console.log("**************************************************** START");
  // console.log(response);
  // console.log("METHOD : ", response?.config?.method);
  // console.log("URL   : ", response?.config?.url);
  const objective: string = response?.config?.headers["OBJECTIVE_TAG"];
  // // console.log("objective   : ", objective);
  if (objective !== undefined && objective.trim().length > 0) {
    if (objective !== "X") {
      // toast.success(objective);
    }

    return;
  }

  if (response?.config?.method === "put") {
    //toast.success("Updated successful");
  } else if (response?.config?.method === "post") {
    if (response?.config?.url?.includes("/list")) {
      // toast("Data grid refreshed");
    } else {
      // toast.success("Created successful");
    }
  }

  // // console.log("****************************************************");
}

const request = {
  get: <T>(url: string) => axios.get<T>(url).then(responseBody),
  put: <T>(url: string, body: any, objective?: string) =>
    axios
      .put<T>(url, body, {
        headers: {
          OBJECTIVE_TAG: objective,
        },
      })
      .then(responseBody),
  delete: <T>(url: string, objective?: string) =>
    axios
      .delete<T>(url, {
        headers: {
          OBJECTIVE_TAG: objective,
        },
      })
      .then(responseBody),
  post: <T>(url: string, body: any, objective?: string) =>
    axios
      .post<T>(url, body, {
        headers: {
          OBJECTIVE_TAG: objective,
        },
      })
      .then(responseBody),
};

//   *  *  *  *  *  *   D R U G   *  *  *  *  *  *

const userLogin = (payload: ILogin) => {
  return request.post<{ token: string }>(`/auth/login`, payload);
};

const userRegistration = (payload: IRegistration) => {
  return request.post<IRegistration>(
    `/auth/registration`,
    payload,
    `${payload.firstName} congrats, you can now login.`
  );
};

const userProfile = () => {
  return request.get<IUser>(`/auth/profile`);
};

const ExternalInterface = {
  userLogin,
  userRegistration,
  userProfile,
};

export default ExternalInterface;
