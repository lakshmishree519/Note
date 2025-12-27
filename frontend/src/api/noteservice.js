import axios from 'axios';
import { getToken } from '../auth/auth.js';

const api = axios.create({
    baseURL:'http://localhost:8080',
    headers:{'Content-Type':'application/json'},
    timeout:9000000
});
export const publicApi = axios.create({
  baseURL: "http://localhost:8080",
  headers:{'Content-Type':'application/json'},
    timeout:10000
});
export const saveNoteList=(payload)=>api.post("/api/keepNotes/saveList",payload);
export const saveNote=(payload)=>api.post("/api/keepNotes/saveNote",payload);
export const getAllNoteList=(userId)=>api.get(`/api/keepNotes/getAllNoteListUser/${userId}`);
export const updateChecked =(payload,id)=>api.put(`/api/keepNotes/updateCheck/${id}`,payload);
export const register = (payload)=>publicApi.post('/api/keepNotes/register',payload);
export const login =(payload)=>publicApi.post('/api/keepNotes/login',payload);
export const updatedNoteList =(payload,id)=>api.put(`/api/keepNotes/updated/${id}`,payload);
export const deleteNoteList = (id)=>api.delete(`/api/keepNotes/delete/${id}`);
export const deleteSingleNote = (noteid)=>api.delete(`/api/keepNotes/deleteSingle/${noteid}`);
api.interceptors.request.use((config)=>{
   
    const token = getToken();
  
    if(token){
        config.headers.Authorization=`Bearer ${token}`;
    }
    return config;
})



export default api;