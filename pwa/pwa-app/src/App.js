import React, {Suspense, lazy, useState} from 'react';
import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import { Header } from './components/Header';

const About = lazy(()=> import("./About"));
const Frontpage = lazy(()=> import("./Frontpage"))

function App() {
  return (
    <BrowserRouter>
      <Header/>
      <Suspense fallback="I am loading..">
        <Routes>
          <Route index element={<Frontpage/>}/>
          <Route path="/about" element={<About/>}/>
        </Routes>
      </Suspense>
    </BrowserRouter>
  );
}

export default App;
