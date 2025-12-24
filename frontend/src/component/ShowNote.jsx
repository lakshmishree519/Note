import React, { useEffect, useState } from "react";
import { updateChecked } from "../api/noteservice.js";
import "./ShowNote.css";
const ShowNote=({onload=[],OnNoteupdate,onEdit})=>{
     const toggle=async(item,id)=>{
        await updateChecked({item},id);
        OnNoteupdate();
     }
    return(
        <div className="show-notes">
              {onload.map((note,i)=>(
                <div className="note-element" key={note.id} onClick={()=>{onEdit(note)}}>
                    <div className="title">{note.title}</div>
                    {note.noteLists.map((item,j)=>(
                       <div key={item.id}
                            className={`note-item-row ${!note.isList ? "text-only" : ""}`}
                        >
                           {note.isList &&<input type="checkbox" checked={item.checked} onChange={(e)=>{
                            e.stopPropagation();
                            toggle(item,item.id);
                           }}/>} 
                           <p>{item.content}</p>
                        </div>
                    ))}
                </div>
              ))}  
        </div>
    );
}


export default ShowNote;