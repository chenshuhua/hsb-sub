import {ElMessage} from 'element-plus'
let tipInstance = null;
const singletonTip = (options) =>{
    if(!tipInstance){
        tipInstance = ElMessage(options);
        setTimeout(() => {
            tipInstance = null
        }, 3000);
    }
}

['error','success','info','warning'].forEach(type => {
    singletonTip[type] = options =>{
        if(typeof options === 'string'){
            options =  {
                message: options
            }
        }
        options.type = type;
        return singletonTip(options);
    }
});
 
export const message = singletonTip;