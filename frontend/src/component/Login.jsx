import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../api/noteservice.js";
import { saveUserId,saveToken } from "../auth/auth.js";

const Login=()=>{
    const [email,setEmail] = useState('');
    const [password,setPassword] = useState('');
    const [loading,setLoading] = useState(false);
    const [error,setError] = useState('');
    const navigate = useNavigate();
    
    const handleLogin=async(e)=>{
       e.preventDefault();
       setError('');
        setLoading(true)
       try{
       console.log(email);
       const response = await login({email,password});
        console.log(response);
       const user = response.data.userId;
       console.log(user);
       const token = response.data.token;
       console.log(token);
        if(!token){
            setError('token not recieved from server');
             setLoading(false);
             return; 
        }
       saveToken(token);
       saveUserId(user);
       console.log("end");
       setLoading(false); 
       console.log("end");
       navigate('/notePage',{replace:true});
    
       }catch(err){
        setError(err?.response?.data||'Login Failed');
  setLoading(false);
       }
    }
    return(<div>
        <form onSubmit={handleLogin}>
            <input type="email" placeholder="email" value={email} onChange={(e)=>setEmail(e.target.value)}/>
                <input type="password" placeholder="password" value={password} onChange={(e)=>setPassword(e.target.value)} />
                {error && <p style={{color:'red'}}>{error}</p>}
            <button type="submit">Submit</button>
        </form>
    </div>);
}

export default Login;