<template>
   <!-- 总容器 -->
   <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>健康档案</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <img src="../assets/report.png">
            <ul>
                <li v-for="orders in ordersArr" :key="orders.orderId" >
                    <div class="left">
                        <i class="fa fa-file-text-o"></i>
                        <div>
                            <p>{{convert(orders.orderDate)}} 体检报告</p>
                            <p>{{orders.hospital.name}}</p>
                        </div>
                    </div>
                    <div class="right" @click="toReport(orders.orderId)">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
            </ul>
        </section>
        
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
    
</template>
 
<script>
//导入需要的基本工具
import {reactive,toRefs,onMounted,ref} from 'vue'
import {useRouter} from 'vue-router'
import Footer from '../components/Footer.vue'
import { getSessionStorage } from '@/common';
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian'
export default {
    setup(){
        //声明需要的数据变量
        const router=useRouter();
       
        const state=reactive({
          users:getSessionStorage('users'),
          ordersArr:[]
        });

        init();

        function init(){
          //根据用户编号，查询已经归档的体检报告
          axios.post('orders/listCireport',{userId:state.users.userId,state:2})
          .then((response)=>{
            state.ordersArr = response.data

          }).catch((error)=>{
              //出错之后
              console.log(error)
          });

        }

        function convert(datestr){
          let arr=datestr.split('-');
          return arr[0]+'年'+arr[1]+'月'+arr[2]+'日';
        }

        //跳转报告页面
        function toReport(id){
          router.push({path:'/report',query:{orderId:id}})
        }
       
        //把数据和函数暴露出去，不然，html访问不到
        return{
          ...toRefs(state),
          convert,
          toReport
     
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
    height: 15.7vw;
}
.bottom-ban{
    width: 100%;
    height: 14.2vw;
}

/*********************** section ***********************/
section{
    width: 100%;
}
section img{
    width: 100%;
    display: block;
}
section ul{
    width: 86vw;
    margin: 0 auto;
}
section ul li{
    width: 100%;
    height: 18vw;
    border-bottom: solid 1px #EEE;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
section ul li .left{
    display: flex;
    align-items: center;
}
section ul li .left i{
    font-size: 8vw;
    color: #6BB9B6;
    margin-right: 3vw;
}
section ul li .left p:first-child{
    color: #555;
    font-size: 4.2vw;
    font-weight: 600;
}
section ul li .left p:last-child{
    color: #999;
    font-size: 3.2vw;
    font-weight: 600;
    margin-top: 1vw;
}
section ul li .right{
    user-select: none;
    cursor: pointer;
}
section ul li .right i{
    color: #999;
    font-size: 6vw;
}
</style>