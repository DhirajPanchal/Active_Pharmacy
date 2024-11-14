//ExternalInterface
import axios, { AxiosError, AxiosResponse } from "axios";
import {
  DEFAULT_LIST_PAYLOAD,
  ListPayload,
  ListResponse,
} from "../model/list.model";
import { toast } from "react-toastify";
import { Drug } from "../model/Drug";

const GATEWAY = "http://localhost:8021";

const STORE_SERVICE_ROUTE = "";

axios.defaults.baseURL = `${GATEWAY}/${STORE_SERVICE_ROUTE}/`;

axios.interceptors.request.use(
  (config) => {
    //console.log("[OUTBOUND] __API (INV) " + config.method + " : " + config.url);

    config.headers["Content-Type"] = "application/json";

    // const token = sessionStorage.getItem("TOKEN");
    // if (token) {
    //   config.headers["Authorization"] = `Bearer ${token}`;
    // }

    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

axios.interceptors.response.use(
  async (response) => {
    responseAnalysis(response);

    // console.log("[INBOUND] __API (INV) RESPONSE", response.data);
    // console.log(response);
    //toast.success("Success");
    return response;
  },
  (error: AxiosError) => {
    const { status, statusText } = error.response!;
    console.error(
      "[INBOUND] __API (INV) ERROR :: " + status + " :: " + statusText
    );
    console.error(error.response);
    toast.error(`ERROR ${status} : ${statusText}`);
    // switch (status) {
    //   case 400:
    //     console.warn("__API (INV) ERROR - 400 ");
    //     break;
    //   case 401:
    //     console.warn("__API (INV) ERROR - 401 Unauthorized ");
    //     break;
    //   case 404:
    //     console.warn("__API (INV) ERROR - 404");
    //     break;
    //   case 500:
    //     console.warn("__API (INV) ERROR - 500 Server Error");
    //     break;
    //   default:
    //     console.warn("__API (INV) ERROR - Unknown");
    //     break;
    // }
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
  // console.log("objective   : ", objective);
  if (objective !== undefined && objective.trim().length > 0) {
    if (objective !== "X") {
      toast.success(objective);
    }

    return;
  }

  if (response?.config?.method === "put") {
    toast.success("Updated successful");
  } else if (response?.config?.method === "post") {
    if (response?.config?.url?.includes("/list")) {
     // toast("Data grid refreshed");
    } else {
      toast.success("Created successful");
    }
  }

  // console.log("****************************************************");
}

const request = {
  get: <T>(url: string, objective?: string) =>
    axios.get<T>(url).then(responseBody),
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

const loadDrugList = (payload: ListPayload = DEFAULT_LIST_PAYLOAD) =>
  request.post<ListResponse<Drug>>(
    `api/v1/list/drug?index=${payload.ui_only.index}&size=${payload.ui_only.size}`,
    payload
  );

const ExternalInterface = {
  loadDrugList,
};

export default ExternalInterface;
