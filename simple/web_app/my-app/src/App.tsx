import React from 'react';
import logo from './logo.svg';
import './App.css';
import Navbar from './components/tailwind/Navbars';
import Pagination from './components/tailwind/Pagination';
import Home from './components/home';
import { Route, Routes } from 'react-router-dom';
import Calendar from './components/pages/calendar';
import Projects from './components/pages/projects';
import Team from './components/pages/team';
import Notifications from './components/pages/notifications';

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route index element={<Home />} />
        <Route path="calendar" element={<Calendar />} />
        <Route path="projects" element={<Projects />} />
        <Route path="team" element={<Team />} />
        <Route path="notifications" element={<Notifications />} />
      </Routes>
      {/* <Pagination/> */}
      {/* <Home/> */}
      {/* <h1 className="text-3xl font-bold underline">
        Hello world!
      </h1> */}
    </>
  );
}

export default App;
