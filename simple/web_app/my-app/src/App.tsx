import React from 'react';
import logo from './logo.svg';
import './App.css';
import Navbar from './components/tailwind/Navbars';
import Pagination from './components/tailwind/Pagination';
import Home from './components/home';

function App() {
  return (
    <>
      <Navbar />
      {/* <Pagination/> */}
      <Home/>
      {/* <h1 className="text-3xl font-bold underline">
        Hello world!
      </h1> */}
    </>
  );
}

export default App;
