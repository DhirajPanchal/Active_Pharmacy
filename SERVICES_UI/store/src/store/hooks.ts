import {
    useDispatch,
    useSelector,
    type TypedUseSelectorHook,
  } from "react-redux";
  import { AppDispatch, RootState } from './store'
  
  type DispatchFunction = () => AppDispatch;
  
  export const appDispatch: DispatchFunction = useDispatch;
  
  export const appSelector: TypedUseSelectorHook<RootState> = useSelector;
  