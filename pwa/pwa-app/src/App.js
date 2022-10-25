import React, {Suspense, lazy} from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import FrontPage from './Frontpage';
import { Header } from './components/Header';

const MovieDetailPage = lazy(()=> import("./MovieDetailPage"));

function App() {
  return (
    <BrowserRouter>
      <Header/>
      <Suspense fallback="I am loading..">
        <Routes>
          <Route index element={<FrontPage/>}/>
          <Route path="/page2" element={<MovieDetailPage/>}/>
        </Routes>
      </Suspense>
    </BrowserRouter>
  );
}

export default App;
