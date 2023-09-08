<template>
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>我的预约</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <ul>
            <li v-for="orders in ordersArr" :key="orders.orderId">
                <div class="left">
                    <p>{{convert(orders.orderDate)}}</p>
                    <p>{{forders.setmeal.name}}</p>
                </div>
                <div class="right" @click="toCancel(orders.orderId)" v-if="currDate<orders.orderDate">
                    取消预约 
                </div>
            </li>
        </ul>
        
        <div class="bottom-ban"></div>
        <!-- <footer>
            <ul>
                <li onclick="location.href='index.html'">
                    <i class="fa fa-home"></i>
                    <p>云医院</p>
                </li>
                <li>
                    <i class="fa fa-opencart"></i>
                    <p>商城</p>
                </li>
                <li>
                    <i class="fa fa-compass"></i>
                    <p>发现</p>
                </li>
                <li onclick="location.href='personal.html'">
                    <i class="fa fa-user"></i>
                    <p>我</p>
                </li>
            </ul>
        </footer> -->
        <Footer></Footer>
    </div>
</template>

<script>
import {reactive,toRefs} from 'vue'
import {useRouter,useRoute} from 'vue-router'
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian'
import Footer from '@/components/Footer.vue';
import { routeLocationKey } from 'vue-router';
import { getSessionStorage,getCurDate } from '@/common';
export default {
    setup(){ 
        //声明需要的数据变量
        const router=useRouter();
   

        const state=reactive({
            ordersArr:[],
            userId:getSessionStorage('users').userId,
            currDate:getCurDate()
        });

        function convert(str){

            let dateArr=str.split('-');
            return dateArr[0]+'年'+dateArr[1]+'月'+dateArr[2]+'日';
        }


        function toCancel(orderId){
            if (!confirm('您确定取消订单吗?')){
                return;
            }
            axios.post('orders/cancelOrders',{
                orderId:orderId
            })
            .then((response)=>{


                if (response.data == 1){
                    alert('已经取消');
                    init();//再次拉取订单列表数据
                }else{
                    alert('取消失败！')
                }

            }).catch((error)=>{
                //出错之后
                console.log(error)
            });
        }

        init();

        function init(){
            axios.post('orders/listOrdersByUserIdByState',{
            userId:state.userId,
            state:1
          })
          .then((response)=>{
            state.ordersArr = response.data
            console.log(state.ordersArr)
          }).catch((error)=>{
              //出错之后
              console.log(error)
          });
        }
       

        return{
            ...toRefs(state),
            // init,
            convert,
            toCancel
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

/*********************** footer ***********************/
footer{
    width: 100%;
    height: 14.2vw;
    box-sizing: border-box;
    border-top: solid 1px #E9E9E9;
    background-color: #FFF;

    position: fixed;
    left: 0;
    bottom: 0;
}
footer ul{
    width: 100%;
    height: 14.2vw;
    display: flex;
    align-items: center;
    justify-content: space-around;
}
footer ul li{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    font-size: 3vw;
    color: #999;

    user-select: none;
    cursor: pointer;
}
footer ul li .fa{
    font-size: 5vw;
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

/*********************** ul ***********************/
ul{
    width: 86vw;
    margin: 0 auto;
}
ul li{
    width: 100%;
    height: 14vw;
    border-bottom: solid 1px #EEE;

    display: flex;
    justify-content: space-between;
    align-items: center;
}
ul li .left{
    user-select: none;
    cursor: pointer;
}
ul li .left p:first-child{
    color: #555;
    font-size: 3.3vw;
}
ul li .left p:last-child{
    color: #333;
    font-size: 4.2vw;
    font-weight: 600;
}
ul li .right{
    font-size: 4vw;
    color: #E6A23C;
}
</style>