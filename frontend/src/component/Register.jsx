
import React,{useState} from "react";
import { register } from "../api/noteservice.js";
import { useNavigate } from "react-router-dom";

const Register=()=>{
    const [email,setEmail] = useState('');
    const [name,setName] = useState('');
    const [password,setPassword] = useState('');
    const [loading,setLoading] = useState(false);
    const [error,setError] = useState(null);
    const navigate = useNavigate();
    const handleRegister=async(e)=>{
          e.preventDefault();   
           setLoading(true)
           setError('');
           try{
           const response = await register({email,name,password});
           setLoading(false);
           navigate('/login',{replace:true});
           }catch(error){
            setError(error?.response?.data||'Registration Failed')
           }
    }
    return(
        <div>
            <h1>Welcome to TodoApp</h1>
            <h2>Please Register</h2>
            <form onSubmit={handleRegister}>
                <input type="text" placeholder="name" value={name} onChange={(e)=>setName(e.target.value)}/>
                <input type="email" placeholder="email" value={email} onChange={(e)=>setEmail(e.target.value)}/>
                <input type="password" placeholder="password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
                <button type="submit">Register</button>
            </form>

        </div>
    );
}

export default Register;