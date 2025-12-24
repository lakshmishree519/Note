import { useState } from 'react'
import './App.css'
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom';
import Login from './component/Login';
import Register from './component/Register';
import NotePage from './component/NotePage';
function App() {

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login/>}></Route>
        <Route path="/register" element={<Register/>}></Route>
        <Route path="/notePage" element={<NotePage/>}></Route>
      </Routes>
    </Router>
  )
}

export default App
