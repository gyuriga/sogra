import React, {  } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainPage from "./pages/MainPage"
import ArrivalPage from "./pages/ArrivalPage"

const App = () => {
	return (
		<div className='App'>
			<BrowserRouter>
				<Routes>
					<Route path="/" element={<MainPage />}></Route>
					<Route path="/arrival" element={<ArrivalPage />}></Route>
				</Routes>
			</BrowserRouter>
		</div>
	);
};

export default App;