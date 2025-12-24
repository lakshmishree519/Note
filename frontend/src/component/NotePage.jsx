import React, { useEffect, useState } from "react";
import { getAllNoteList } from "../api/noteservice";
import "./NotePage.css";
import CreateNote from "./CreateNote";
import ShowNote from "./ShowNote";
import { getUserId } from "../auth/auth";
const NotePage=()=>{
    const [notes,setNotes] = useState([]);
    const [editingNote,setEditingNote] = useState(null);

    useEffect(() => {
      console.log("Notes updated:", notes);
    }, [notes]);

    useEffect(()=>{
        loadNotes();
    },[]);
useEffect(()=>{
        console.log("editing",editingNote);
    },[editingNote]);
    const loadNotes=async ()=>{
        try{ 
              const userId = Number(getUserId());
              const response = await getAllNoteList(userId);
              setNotes(response.data);
        }catch(e){
              console.log(e)
        }
    }

    return(
        <div className="sample">
       <CreateNote onNoteCreated={loadNotes}/>
       <ShowNote onload={notes} OnNoteupdate={loadNotes} onEdit={(note)=>setEditingNote(note)}/>
      {editingNote &&  (<CreateNote editMode  note={editingNote} onClose={()=>setEditingNote(null)} onNoteCreated={()=>{setEditingNote(null);loadNotes();}}/>)}
    </div>
    );
    
}

export default NotePage;