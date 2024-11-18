import { configureStore } from "@reduxjs/toolkit";
import drugsSlice from "./slice/drugs/drugs-slice";

export const appStore = configureStore({
  reducer: {
    drugs: drugsSlice.reducer,
  },
});

export * from './slice/drugs/drugs-async-thunks'

export type AppDispatch = typeof appStore.dispatch;

export type RootState = ReturnType<typeof appStore.getState>;