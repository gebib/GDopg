import '../styles/App.css';
import {useState} from "react";
import DataService from "../service/DataService";

function App() {

    const [fromCurrency, setFromCurrency] = useState('');
    const [toCurrency, setToCurrency] = useState('');
    const [currencyDate, setCurrencyDate] = useState('');
    const [currencyAmmount, setCurrencyAmmount] = useState('');

    const [eschangeResult, setExchcangeResult] = useState('');


    const createRequestData = (e) => {
        e.preventDefault();
        const urlParamData = {from: fromCurrency, to: toCurrency, ammount: currencyAmmount, date: currencyDate};
        DataService.getCurrency(urlParamData).then((response) => {
            console.log("result:://", response.data);
            setExchcangeResult(response.data);
        }).catch((error) => {
            console.log("/////::Error save form: ", error);
        });
    };


    return (
        <div className={"container"}>
            <h1>Currency exchanger via Fixer API</h1>
            <form id={"inputForm1"} onSubmit={createRequestData}>
                <div className="row">
                    <div className="col-lg-3 my-1">
                        <input
                            type="text"
                            placeholder="from"
                            className="form-control"
                            onChange={(e) => {
                                setFromCurrency(e.target.value)
                            }}/>
                    </div>
                    <div className="col-lg-3 my-1">
                        <input
                            type="text"
                            placeholder="to"
                            className="form-control"
                            onChange={(e) => {
                                setToCurrency(e.target.value)
                            }}/>
                    </div>
                    <div className="col-lg-3 my-1">
                        <input
                            type="number"
                            placeholder="ammount"
                            className="form-control"
                            onChange={(e) => {
                                setCurrencyAmmount(e.target.value)
                            }}/>
                    </div>
                    <div className="col-lg-3 my-1">
                        <input
                            type="text"
                            placeholder="date"
                            className="form-control"
                            onChange={(e) => {
                                setCurrencyDate(e.target.value)
                            }}/>
                    </div>
                </div>
                <div className="row">
                    <div className="col-lg-3 mt-3">
                        <button className="btn btn-success" form="inputForm1" type="submit">Submit</button>
                    </div>

                </div>
            </form>
        </div>
    );
}

export default App;
