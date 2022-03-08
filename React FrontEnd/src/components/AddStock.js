import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import StockService from "../services/StockService";

function AddStock() {
  const navigate = useNavigate();
  let [data, setData] = useState({
    ticker: "",
    company: "",
    quantity: "",
    purchasePrice: "",
  });
  const handleChange = (event) => {
    setData((x) => {
      return { ...x, [event.target.id]: event.target.value };
    });
  };
  const saveStock = (e) => {
    e.preventDefault();
    let stock = {
      ticker: data.ticker,
      company: data.company,
      quantity: data.quantity,
      purchasePrice: data.purchasePrice,
    };
    console.log(stock);
    StockService.createStock(stock)
      .then((res) => {
        navigate("/portfolio");
      })
      .catch((err) => {
        console.log("record not saved.");
      });
  };
  return (
    <div className="body3">
      <div className="form">
        <div class="title">Holding</div>
        <div class="subtitle">Let's add new stock to your holding</div>
        <form>
          <div className="input-container ic1">
            <input
              placeholder=" "
              id="ticker"
              className="input"
              value={data.ticker}
              onChange={handleChange}
            />
            <div class="cut"></div>
            <label className="placeholder">Stock Ticker</label>
          </div>
          <div className="input-container ic2">
            <input
              placeholder=" "
              id="company"
              className="input"
              value={data.company}
              onChange={handleChange}
            />
            <div class="cut"></div>
            <label className="placeholder">Company Name</label>
          </div>
          <div className="input-container ic2">
            <input
              placeholder=" "
              id="quantity"
              className="input"
              value={data.quantity}
              onChange={handleChange}
            />
            <div class="cut cut-short"></div>
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
            <div class="cut"></div>
            <label className="placeholder">Purchase Price</label>
          </div>
          <button className="submit" onClick={saveStock}>
            {" "}
            Save{" "}
          </button>
          <button className="submit" onClick={() => navigate("/portfolio")}>
            Cancel
          </button>
        </form>
      </div>
    </div>
  );
}

export default AddStock;
