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
import Create from './components/pages/create';
import DefaultLayout from './components/containers/default';
import CategoryCreatePage from './components/categories/create';
import DefaultHeader from './components/containers/default/DefaultHeader';

function App() {
  return (
    <>
      <DefaultHeader />
      <Routes>
        <Route path="/" element={<DefaultLayout/>}/>
        <Route index element={<Home />} />
        <Route path="calendar" element={<Calendar />} />
        <Route path="projects" element={<Projects />} />
        <Route path="team" element={<Team />} />
        <Route path="notifications" element={<Notifications />} />
        <Route path="categories/create" element={<CategoryCreatePage />} />
      </Routes>
    </>
  );
}

export default App;
