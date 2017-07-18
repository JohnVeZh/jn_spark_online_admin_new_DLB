﻿/*
Copyright Scand LLC http://www.scbr.com
To use this component please contact info@scbr.com to obtain license
*/ 
/*
Purpose: item edit extension
*/


/**
*     @desc: enable editing of item's names
*     @param:  mode - true/false
*     @edition: Professional
*     @type: public
*     @topic: 0
*/
dhtmlXTreeObject.prototype.enableItemEditor=function(mode){
        this._eItEd=convertStringToBoolean(mode);
        if (!this._eItEdFlag){
            var self=this;
            this._edn_click_IE=true;
            this._edn_dblclick=true;
            this._ie_aFunc=this.aFunc;
            this._ie_dblclickFuncHandler=this.dblclickFuncHandler;

            this.dblclickFuncHandler=function (a,b) {
                if (self._edn_dblclick) self._editItem(a,b); };

            this.aFunc=function (a,b) {
                self._stopEditItem(a,b);
                    if ((self.ed_hist_clcik==a)&&(self._edn_click_IE))
                        self._editItem(a,b);
                self.ed_hist_clcik=a;
                if (self._ie_aFunc) self._ie_aFunc(a,b);
                };

            this.setOnClickHandler=this.__setOnClickHandler;
            this.setOnDblClickHandler=this.__setOnDblClickHandler;
            this._eItEdFlag=true;

            }
        };

/**
*     @desc: set onEdit handler
*     @param:  func - function which will be called on edit related events
*     @edition: Professional
*     @type: public
*     @event:  onEdit
*     @eventdesc: Event occurs on 4 different stages of edit process: before editing started (cancelable), after editing started, before closing (cancelable), after closed
*     @eventparam: state - 0 before editing started , 1 after editing started, 2 before closing, 3 after closed
*     @eventparam: id - id of edited items
*     @eventparam: tree - tree object
*     @eventparam: value - for stage 0 and 2, value of editor
*     @eventreturn: for stages 0 and 2; true - confirm opening/closing, false - deny opening/closing;  text - edit value
*     @topic: 0
*/
dhtmlXTreeObject.prototype.setOnEditHandler=function(func){
        if (typeof(func)=="function") this._onITCFunc=func; else this._onITCFunc=eval(func);
        };

/**
*     @desc: define which events must start editing
*     @param:  click_IE - click on already selected item - true/false [true by default]
*     @param:  dblclick - click on already selected item - true/false [true by default]
*     @edition: Professional
*     @type: public
*     @topic: 0
*/
dhtmlXTreeObject.prototype.setEditStartAction=function(click_IE, dblclick){
        this._edn_click_IE=convertStringToBoolean(click_IE);
        this._edn_dblclick=convertStringToBoolean(dblclick);
        };

dhtmlXTreeObject.prototype._stopEdit=function(a){
    if  (this._editCell){
        this.dADTempOff=this.dADTempOffEd;
        if (this._editCell.id!=a){

        var editText=true;
        if (this._onITCFunc)
            editText=this._onITCFunc(2,this._editCell.id,this,this._editCell.span.childNodes[0].value);
        if (editText===true)
            editText=this._editCell.span.childNodes[0].value;
        else if (editText===false) editText=this._editCell._oldValue;

        this._editCell.span.innerHTML=editText;
        this._editCell.label=this._editCell.span.innerHTML;
        this._editCell.span.className="standartTreeRow";
        this._editCell.span.parentNode.className="standartTreeRow";
        temp.span.onclick=function(){};
            if (this._onITCFunc) this._onITCFunc(3,this._editCell.id,this);
            this._editCell=null;
        }
    }
}

dhtmlXTreeObject.prototype._stopEditItem=function(id,tree){
    this._stopEdit(id);
};

/**
*     @desc:  switch current edited item back to normal state
*     @edition: Professional
*     @type: public
*     @topic: 0
*/

dhtmlXTreeObject.prototype.stopEdit=function(){
    if (this._editCell)
        this._stopEdit(this._editCell.id+"_non");
}

/**
*     @desc: enable editing of item's names
*     @param:  mode - true/false
*     @edition: Professional
*     @type: public
*     @topic: 0
*/
dhtmlXTreeObject.prototype.editItem=function(id){
    this._editItem(id,this);
}

dhtmlXTreeObject.prototype._editItem=function(id,tree){
    if (this._eItEd){
        this._stopEdit();
        this.dADTempOffEd=this.dADTempOff;
        this.dADTempOff=false;

        temp=this._globalIdStorageFind(id);

        var editText=true;
        if (this._onITCFunc)
            editText=this._onITCFunc(0,id,this,temp.span.innerHTML);
        if (editText===true)
            editText=temp.span.innerHTML;
        else if (editText===false) return;


        this._editCell=temp;
        temp._oldValue=editText;
        temp.span.innerHTML="<input type='text' class='intreeeditRow' />";

        temp.span.childNodes[0].value=editText;

        temp.span.childNodes[0].onselectstart=function(e){
            (e||event).cancelBubble=true;
            return true;
        }
        temp.span.childNodes[0].onmousedown=function(e){
            (e||event).cancelBubble=true;
            return true;
        }

        temp.span.childNodes[0].focus();
        temp.span.childNodes[0].focus();
        temp.span.onclick=function (e){ (e||event).cancelBubble=true; return false; };
        temp.span.className="";
        temp.span.parentNode.className="";

        var self=this;
        temp.span.childNodes[0].onkeypress=function(e){
            if (!e) e=window.event;
            if (e.keyCode==13){
                 self._stopEdit(-1);
				 }
        }
        if (this._onITCFunc) this._onITCFunc(1,id,this);
    }
    else
        if (this._ie_dblclickFuncHandler) this._ie_dblclickFuncHandler(id,tree);
};



dhtmlXTreeObject.prototype.__setOnDblClickHandler=function(func){
    if (typeof(func)=="function") this._ie_dblclickFuncHandler=func; else this._ie_dblclickFuncHandler=eval(func);
};

dhtmlXTreeObject.prototype.__setOnClickHandler=function(func){
    if (typeof(func)=="function") this._ie_aFunc=func; else this._ie_aFunc=eval(func);
};

