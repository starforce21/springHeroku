import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import StockService from "../services/StockService";

function UpdateStock() {
  const navigate = useNavigate();
  let { ticker } = useParams();
  console.log(ticker)
  let [data, setData] = useState({
    quantity: "",
    purchasePrice: "",
  });
  const handleChange = (event) => {
    setData((x) => {
      return { ...x, [event.target.id]: event.target.value };
    });
  };
  const updateStock = (e) => {
    e.preventDefault();
    let stock = {
      ticker: ticker,
      quantity: data.quantity,
      purchasePrice: data.purchasePrice,
    };
    StockService.updateStock(stock, stock.ticker).then(() => {
      navigate("/portfolio");
    });
  };
  return (
    <div className="body3">
      <div className="form">
        <div className="title">Holding</div>
        <div className="subtitle">Let's update this stock!</div>
        <form>
          <div className="input-container ic1">
            <input
              placeholder=" "
              id="ticker"
              readOnly={true}
              className="input"
              value={ticker}
            />
            <div className="cut"></div>
            <label className="placeholder">Stock Ticker</label>
          </div>
          <div className="input-container ic2">
            <input
              placeholder=" "
              id="quantity"
              className="input"
              value={data.quantity}
              onChange={handleChange}
            />
            <div className="cut cut-short"></div>
            <label className="placeholder">Quantity </label>
          </div>
          <div className="input-container ic2">
            <input
              placeholder=" "
              id="purchasePrice"
              className="input"
              value={data.purchasePrice}
              onChange={handleChange}
            />
            <div className="cut"></div>
            <label className="placeholder">Purchase Price</label>
          </div>
          <button className="submit" onClick={updateStock}>
            {" "}
            Update{" "}
          </button>
          <button className="submit" onClick={() => navigate("/portfolio")}>
            Cancel
          </button>
        </form>
      </div>
    </div>
  );
}

export default UpdateStock;
