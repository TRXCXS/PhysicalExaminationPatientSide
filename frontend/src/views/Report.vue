<template>
   <!-- 总容器 -->
   <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>体检报告</p>
            <div></div>
        </header>

        <nav>
            <div :class="{active:divVisible=='general'}" @click="navEvent('general')">总检结论</div>
            <div :class="{active:divVisible=='detail'}" @click="navEvent('detail')">报告详情</div>
        </nav>

        <div class="top-ban"></div>

        <div class="nav-content-item" v-if="divVisible=='general'">
            <div class="item">
                <div class="title">异常项</div>
                <ul>
                    <li v-for="cidl_e in errorCidlReportArr" :key="cidl_e.cidrId">
                        <div class="indications">
                            <div class="left">
                                <div>异</div>
                                <p>{{ cidl_e.name }}</p>
                            </div>
                            <div class="right">
                                <p>{{cidl_e.value}}{{ cidl_e.unit }}</p>
                                <p>{{ cidl_e.normalValueString }}</p>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="item">
                <div class="title">一、尊敬的顾客，您本次体检结论如下：</div>
                <ul>
                    <li class="conclusion-title" 
                    v-for="(overallResult,index) in overallResultArr" 
                    :key="overallResult.orId">
                    {{index+1+'、'+ overallResult.title}}    
                </li>
                  
                </ul>
            </div>
            <div class="item">
                <div class="title">二、尊敬的顾客，您本次体检建议信息日下：</div>
                <ul>
                    <li class="conclusion-content"  v-for="(overallResult,index) in overallResultArr" 
                    :key="overallResult.orId">
                        <h3>{{index+1+'、'+ overallResult.title}}</h3>
                        <p>
                            {{overallResult.content}}
                        </p>
                    </li>
                </ul>
            </div>
        </div>

        <div class="nav-content-item" v-if="divVisible == 'detail'">
            <div class="item" v-for="ci in cidlReportArr" :key="ci.cirId">
                <div class="title">{{ci.ciName}}</div>
                <ul>
                    <li v-for="cdr in ci.detailedreportList" :key="cdr.cidrId">
                        <div class="indications" v-if="cdr.type!=4">
                            <div class="left">
                                <div v-if="cdr.isError == 1">异</div>
                                <p>{{cdr.name}}</p>
                            </div>
                            <div class="right">
                                <p>{{cdr.value}}{{ cdr.unit }}</p>
                                <p>正常值范围：{{cdr.normalValueString}}</p>
                            </div>
                        </div>
                        <div class="indications-type-4" v-if="cdr.type == 4">
                            <div>
                                <p>{{cdr.name}}</p>
                            </div>
                            <div>
                                <p>{{ cdr.value }}</p>
                            </div>
                        </div>
                    </li>
                    
                </ul>
            </div>
          
        </div>
        
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
//导入需要的基本工具
import {reactive,toRefs,onMounted,ref} from 'vue'
import {useRouter , useRoute} from 'vue-router'
import Footer from '../components/Footer.vue'
import { getSessionStorage } from '@/common';
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian'
export default {
    setup(){
        //声明需要的数据变量
        const router=useRouter();
        const route = useRoute();
       
        const state=reactive({
            users:getSessionStorage('users'),
            overallResultArr:[],    //存放总检结论列表数据
            errorCidlReportArr:[],  //,存放体检明细列表数据，注意只限于异常项
            cidlReportArr:[],       //,存放体检明细列表数据
            divVisible:'general',
            cireportArr:[]
        });

        init();

        function init(){

        axios.post('overallResult/getOverallResultByoOrderId',{orderId:route.query.orderId})
          .then((response)=>{
            state.overallResultArr=response.data
            console.log(overallResultArr)
          }).catch((error)=>{
              //出错之后
              console.log(error)
          });


        axios.post('cidetailedReport/listCidetailedReportByOrderId',{orderId:route.query.orderId})
          .then((response)=>{
            state.cidlReportArr=response.data
            console.log(cidlReportArr);
            for(let i=0;i<state.cidlReportArr.length;i++){
                if(state.cidlReportArr[i].isError==1){
                    state.errorCidlReportArr.push(state.cidlReportArr[i]);
                }
            }
            console.log(state.errorCidlReportArr);
          }).catch((error)=>{
              //出错之后
              console.log(error)
          });


          axios.post('cireport/getCireport',{orderId:route.query.orderId})
            .then((response)=>{
                state.cireportArr=response.data;
                console.log('1');
                console.log(state.cidlReportArr);
                console.log('2'); 
            }).catch((error)=>{
                console.log(error) 
            });
        }

        //界面切换事件
        function navEvent(str){
            state.divVisible = str;
        }
       
       
        //把数据和函数暴露出去，不然，html访问不到
        return{
            ...toRefs(state),
            navEvent
     
        }
    },
    components:{
       Footer
    }
}
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #F9F9F9;
}

/*********************** header ***********************/
header{
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    top: 0;

    display: flex;
    align-items: center;
    justify-content: space-between;

    box-sizing: border-box;
    padding: 0 3.6vw;
}
header .fa{
    font-size: 8vw;
}


/*********************** common样式 ***********************/
.top-ban{
    width: 100%;
    height: 31.7vw;
}
.bottom-ban{
    width: 100%;
    height: 14.2vw;
}

/*********************** nav ***********************/
nav{
    width: 100%;
    height: 16vw;
    display: flex;
    background-color: #F9F9F9;

    position: fixed;
    left: 0;
    top: 15.7vw;
}
nav div{
    flex: 1;
    height: 14vw;
    box-sizing: border-box;

    text-align: center;
    line-height: 14vw;
    font-size: 4.2vw;
    font-weight: 600;
    color: #555;
}

.active{
    border-bottom: solid 2px #137E92;
    color: #137E92;
}

/*********************** nav-content-item ***********************/
.nav-content-item .item{
    width: 92vw;
    margin: 0 auto;
    margin-top: 3vw;
    margin-bottom: 3vw;
    overflow: hidden;
    border-radius: 3vw;
    background-color: #FFF;
}
.nav-content-item .item .title{
    width: 100%;
    height: 10vw;
    background-color: #7BAFD7;
    line-height: 10vw;
    box-sizing: border-box;
    padding-left: 4vw;
    font-size: 4.2vw;
    color: #FFF;
}

.nav-content-item .item ul{
    width: 100%;
}

/****** 数值型检查项样式 ******/
.nav-content-item .item ul li{
    border-bottom: solid 1px #EEE;
}
.nav-content-item .item ul li:last-child{
    border-bottom: none;
}
.nav-content-item .item ul li .indications{
    width: 100%;
    height: 14vw;
    padding: 0 3vw;
    display: flex;
    align-items: center;
    font-size: 3.2vw;
    color: #333;
}

.nav-content-item .item ul li .indications .left{
    flex: 1;
    display: flex;
}
.nav-content-item .item ul li .indications .left div{
    background-color: #BA634E;
    color: #FFF;
    padding: 0.2vw 0.6vw;
    border-radius: 0.6vw;
}
.nav-content-item .item ul li .indications .left p{
    font-weight: 600;
    margin-left: 2vw;
}
.nav-content-item .item ul li .indications .right{
    flex: 1;
}
.nav-content-item .item ul li .indications .right p:last-child{
    color: #999;
}
/****** 数值型检查项样式 ******/

/****** 描述型检查项样式 ******/
.nav-content-item .item ul li .indications-type-4{
    width: 100%;
    box-sizing: border-box;
    padding: 0 3vw;

    font-size: 3.2vw;
    color: #333;
}
.nav-content-item .item ul li .indications-type-4 div{
    margin: 5vw 0;
}
.nav-content-item .item ul li .indications-type-4 div:first-child{
    font-weight: 600;
}
.nav-content-item .item ul li .indications-type-4 div:last-child{
    color: #999;
}
/****** 描述型检查项样式 ******/

.nav-content-item .item ul .conclusion-title{
    width: 100%;
    height: 12vw;
    box-sizing: border-box;
    border-bottom: solid 1px #EEE;
    padding: 0 3vw;

    display: flex;
    align-items: center;
    font-size: 4vw;
    color: #555;
}
.nav-content-item .item ul .conclusion-title:last-child{
    border-bottom: none;
}

.nav-content-item .item ul .conclusion-content{
    width: 100%;
    box-sizing: border-box;
    border-bottom: solid 1px #EEE;
    padding: 4vw 3vw;
    font-size: 3.6vw;
    color: #555;
}
.nav-content-item .item ul .conclusion-content:last-child{
    border-bottom: none;
}
.nav-content-item .item ul .conclusion-content h3{
    font-size: 4vw;
    font-weight: 600;
    margin-bottom: 2vw;
}
.nav-content-item .item ul .conclusion-content p{
    text-indent: 8vw;
}

</style>