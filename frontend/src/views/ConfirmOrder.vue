<template>
 <!-- 总容器 -->
 <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>确认您的订单</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <div class="title">
                <p>体检人信息</p>
            </div>
            <table>
                <tr>
                    <td>姓名:</td>
                    <td>{{ users.realName }}</td>
                </tr>
                <tr>
                    <td>证件号码:</td>
                    <td>{{ users.identityCard }}</td>
                </tr>
                <tr>
                    <td>出生日期:</td>
                    <td>{{ users.birthday.split('-')[0] }}年{{ users.birthday.split('-')[1] }}月{{ users.birthday.split('-')[2] }}日</td>
                </tr>
                <tr>
                    <td>手机号码:</td>
                    <td>{{ users.userId }}</td>
                </tr>
            </table>
            <div class="title">
                <p>体检日期</p>
            </div>
            <table>
                <tr>
                    <td>{{ selectDay.split('-')[0] }}年{{ selectDay.split('-')[1] }}月{{ selectDay.split('-')[2] }}日</td>
                </tr>
            </table>
            <div class="title">
                <p>体检机构</p>
            </div>
            <table>
                <tr>
                    <td colspan="2">{{ hospital.name }}</td>
                </tr>
                <tr>
                    <td>营业时间:</td>
                    <td>{{ hospital.businessHours }}</td>
                </tr>
                <tr>
                    <td>采血截止:</td>
                    <td>{{ hospital.deadline }}</td>
                </tr>
                <tr>
                    <td>机构电话:</td>
                    <td>{{ hospital.telephone }}</td>
                </tr>
                <tr>
                    <td>机构地址:</td>
                    <td>{{ hospital.address }}</td>
                </tr>
            </table>
            <div class="title">
                <p>套餐类型</p>
            </div>
            <table>
                <tr>
                    <td>{{ setmeal.name }}</td>
                </tr>
            </table>
        </section>
        
        <div class="bottom-btn">
            <div class="first">实付款: <span>￥{{ users.type==2?0:setmeal.price }}</span></div>
            <div class="last" @click="toSaveOrders">确认支付</div>
        </div>

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
import {reactive,shallowReactive,toRefs} from 'vue'
import {useRouter,useRoute} from 'vue-router'
import axios from 'axios'
axios.defaults.baseURL='http://localhost:8080/tijian'
import Footer from '@/components/Footer.vue';
import { routeLocationKey } from 'vue-router';
import { getSessionStorage } from '@/common';
export default {
    setup(){ 
        //声明需要的数据变量
        const router=useRouter();
        const route=useRoute();


        //定义当前日期
        const currentCalendar = new Date();

        const state=reactive({ 
            hpId:route.query.hpId,
            smId:route.query.smId,
            selectDay:route.query.selectDay,
            users:getSessionStorage('users'),
            hospital:{
              
            },
            setmeal(){

            }        
        });

        init();

        function init(){ 
          
          axios.post('hospital/getHospitalById',{hpId:state.hpId})
          .then((response)=>{
            console.log(response.data)

            state.hospital = response.data

          }).catch((error)=>{
              //出错之后
              console.log(error)
          });

          axios.post('setmeal/getSetmealById',{smId:state.smId})
          .then((response)=>{
            state.setmeal = response.data
          }).catch((error)=>{
              //出错之后
              console.log(error)
          });

         
        }
     
        function toSaveOrders(){
          //订单数据持久化到数据库
          axios.post('orders/saveOrders',{
            orderDate:state.selectDay,
            userId:state.users.userId,
            hpId:state.hpId,
            smId:state.smId
          })
          .then((response)=>{
            if (response.data == 1 ){
              router.push('/appointmentSuccess')
            }else{
              alert('预约失败')
            }
            
          }).catch((error)=>{
              //出错之后
              console.log(error)
          });
        
        }

        return{
          ...toRefs(state),
          init,
          toSaveOrders
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
    height: 26.2vw;
}

/*********************** section ***********************/
section{
    width: 86vw;
    margin: 0 auto;
}
section .title{
    width: 100%;
    height: 12vw;
    border-bottom: solid 1px #EEE;

    display: flex;
    align-items: center;
}
section .title p{
    height: 3.4vw;
    line-height: 3.4vw;
    font-size: 4.2vw;
    font-weight: 600;
    box-sizing: border-box;
    padding-left: 3vw;
    border-left: solid 2px #127A90;
}
section table{
    font-size: 3.6vw;
    color: #555;
    margin-top: 2vw;
}
section table tr{
    line-height: 8vw;
}
section table tr td:first-child{
    width: 22%;
}

/*********************** bottom-btn ***********************/
.bottom-btn{
    width: 100%;
    height: 12vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    bottom: 14.2vw;

    display: flex;
}
.bottom-btn .first{
    flex: 2;
    line-height: 12vw;
    font-size: 4.6vw;
    box-sizing: border-box;
    padding-left: 6vw;
}
.bottom-btn .first span{
    color: #F77B2D;
}
.bottom-btn .last{
    flex: 1;
    background-color: #117C94;
    line-height: 12vw;
    text-align: center;
    font-size: 5vw;
    color: #FFF;

    user-select: none;
    cursor: pointer;
}
</style>