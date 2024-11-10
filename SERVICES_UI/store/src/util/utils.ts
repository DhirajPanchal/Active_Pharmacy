const localDateTimeFormatter = (dateString: string): string => {
    const date = new Date(dateString);
    return date.toLocaleDateString() + "  " + date.toLocaleTimeString();
  };
  
  export const dateValueGetter = (params: any): string => {
    // console.log(params);
    let dateStr = "";
    if (params) {
      dateStr = localDateTimeFormatter(params);
    }
    return dateStr;
  };