import React, { useState, useRef, useEffect } from "react";
import { saveNote, saveNoteList,updatedNoteList } from "../api/noteservice";
import "./CreateNote.css";
import { getUserId } from "../auth/auth.js";


const CreateNote = ({ onNoteCreated,editMode,note,onClose}) => {
  const [mode, setMode] = useState("collapsed"); // collapsed | text | list
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [items, setItems] = useState([{ content: "", checked: false }]);
  const [color, setColor] = useState("#ffffff");
  const [error,setError] = useState(null);
  const noteRef = useRef(null);

  /* --------- REFS TO HOLD LATEST VALUES (IMPORTANT) --------- */
  const titleRef = useRef(title);
  const contentRef = useRef(content);
  const itemsRef = useRef(items);

  /* --------- SYNC STATE -> REF --------- */
  useEffect(() => {
    titleRef.current = title;
  }, [title]);

  useEffect(() => {
    contentRef.current = content;
  }, [content]);

  useEffect(() => {
    itemsRef.current = items;
  }, [items]);

  /* --------- OUTSIDE CLICK HANDLER --------- */
  useEffect(() => {
    if (mode === "collapsed") return;
    const handleClickOutside = (e) => {
      if (
        noteRef.current &&
        !noteRef.current.contains(e.target)
      ) {
        handleClose();
      }
    };
    document.addEventListener("mousedown", handleClickOutside);

    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [mode]);


  useEffect(()=>{
    // console.log(note,"note");
    if(editMode&&note){
        setMode(note.isList?"list":"text");
        setContent(!note.isList?note.noteLists?.[0]?.content||"":"");
        setTitle(note.title||"");
        setItems(note.noteLists||[]);
        setColor(note.color || "#ffffff");
        
    }
  },[editMode,note]);

  /* --------- HANDLE CLOSE --------- */
  const handleClose = () => {
    if(editMode){
        if(mode==="list") updateList();
        if(mode==="text") updateText();
    }else{
    if (mode === "list") saveList();
    if (mode === "text") saveText();
    }
    reset();
  };

  /* --------- SAVE LIST NOTE --------- */
  const saveList = async () => {
    try {
      const userId =  Number(getUserId());

      const payload = {
        title: titleRef.current,
        isList: true,
        noteLists: itemsRef.current,
        user: { userId }
      };

      await saveNoteList(payload);
      onNoteCreated && onNoteCreated();
    } catch (error) {
      console.log("saveList error", error);
    }
  };
  const updateList = async ()=>{
    try{ 
        const userId =  Number(getUserId());
        const payload={
        title: titleRef.current,
        isList: true,
        noteLists: itemsRef.current,
        user: { userId}
        };

        await updatedNoteList(payload,note.id);
         onNoteCreated && onNoteCreated();
    }catch(e){
          setError("note list i snot updated",e);
          onClose();
    }
  }

  /* --------- SAVE TEXT NOTE --------- */
  const saveText = async () => {
    try {
         const noteLists = [
        { content: contentRef.current, checked: false }
      ];
   const userId =  Number(getUserId());
     console.log(userId);
      const payload = {
        title: titleRef.current,
        isList: false,
        noteLists,
        user: { userId }
      };

     const res =await saveNote(payload);
      onNoteCreated && onNoteCreated();
    } catch (error) {
      console.log("saveText error", error);
    }
  };

  const updateText =async ()=>{
    try{
    const noteLists = [
        { id:note.noteLists?.[0]?.id,content: contentRef.current, checked: false }
      ];
   const userId =  Number(getUserId());
     console.log(userId);
      const payload = {
        title: titleRef.current,
        isList: false,
        noteLists,
        user: { userId }
      };
       const res =await updatedNoteList(payload,note.id);
       onNoteCreated && onNoteCreated();
    }catch (error) {
      console.log("saveText error", error);
    }
  }

  /* --------- RESET --------- */
  const reset = () => {
    setMode("collapsed");
    setTitle("");
    setContent("");
    setItems([{ content: "", checked: false }]);
    setColor("#ffffff");
  };


   const renderEditor =()=>{
    return(
     <div ref={noteRef} className="expanded-container" key="list" style={{backgroundColor:color}}>
        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
     {mode==="list"?(
        <>
         <div className="add-list-item" onClick={() => setItems([...items, { content: "", checked: false }])
  }>
  <span className="material-icons-outlined add-icon">add</span>
  <span className="add-text">List item</span>
</div>

        {items.map((item,i)=>(<div className="list-elements" key={item.id}>
            <input
              type="checkbox"
              checked={item.checked}
              onChange={() => {
                const copy = [...items];
                copy[i].checked = !copy[i].checked;
                setItems(copy);
              }}
            />
            <input
              type="text"
              value={item.content}
              onChange={(e) => {
                const copy = [...items];
                copy[i].content = e.target.value;
                setItems(copy);
              }}
            />
          </div>))}
     
     </>):(<input
        type="text"
        placeholder="Content"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />)}
      <div>
           <button title="Remind me">
          <span className="material-icons-outlined">notifications</span>
          </button>
           <button title="Collaborator">
          <span className="material-icons-outlined">person_add</span>
        </button>

        <button title="Change color" onClick={() => setColor("#f28b82")}>
          <span className="material-icons-outlined">palette</span>
        </button>

        <button title="Add image">
          <span className="material-icons-outlined">image</span>
        </button>

        <button title="Archive">
          <span className="material-icons-outlined">archive</span>
        </button>
                <button title="More">
          <span className="material-icons-outlined">more_vert</span>
        </button>

        <div className="action-right">
          <button title="Undo">
            <span className="material-icons-outlined">undo</span>
          </button>
          <button title="Redo">
            <span className="material-icons-outlined">redo</span>
          </button>
          <button className="close-btn" onClick={handleClose}>
            Close
          </button>
        </div>

      </div>
      </div> 
    );
   }

   if (editMode) {
    return (
      <div className="modal-overlay">
        <div className="modal-card">{renderEditor()}</div>
      </div>
    );
  }
  /* --------- COLLAPSED VIEW --------- */
  if (mode === "collapsed") {
    return (
      <div
        className="collapsed-container"
        onClick={() => setMode("text")}
        key="collapsed"
      >
        <input
          type="text"
          placeholder="Take a note"
          readOnly
        />
        <div className="collapsed-icons">
          <button
            onClick={(e) => {
              e.stopPropagation();
              setMode("list");
            }}
          >
            ☑
          </button>
        </div>
      </div>
    );
  }

return renderEditor();
};

export default CreateNote;  