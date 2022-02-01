import axios from "axios";

class DataFromDb {

    //http://localhost:8080/ce/v1?from=nok&to=etb&ammount=5&date=latest

    //Create in new data
    getCurrency = (urlParamData) => {
        const baseUrl = "http://localhost:8080/ce/v1";
        const from = "?from=" + urlParamData.from;
        const to = "&to=" + urlParamData.to;
        const ammount = "&ammount=" + urlParamData.ammount;

        let date = "";

        if (urlParamData.date.length > 0) {
            date = "&date=" + urlParamData.date;
        }

        const requestUrl = baseUrl + from + to + ammount + date;

        return axios.get(requestUrl);
    };
}

export default new DataFromDb();