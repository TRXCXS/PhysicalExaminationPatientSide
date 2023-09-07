<template>
  <!-- 总容器 -->
  <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>选择体检日期</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <div class="date-box">
                <i class="fa fa-caret-left"></i>
                <p>2022年1月</p>
                <i class="fa fa-caret-right"></i>
            </div>
            <table>
                <tr>
                    <th>日</th>
                    <th>一</th>
                    <th>二</th>
                    <th>三</th>
                    <th>四</th>
                    <th>五</th>
                    <th>六</th>
                </tr>
            </table>
            <ul>
                <li>
                    <p></p>
                    <p></p>
                </li>
                <li>
                    <p></p>
                    <p></p>
                </li>
                <li>
                    <p></p>
                    <p></p>
                </li>
                <li>
                    <p></p>
                    <p></p>
                </li>
                <li>
                    <p></p>
                    <p></p>
                </li>
                <li>
                    <p></p>
                    <p></p>
                </li>
                <li>
                    <p>1</p>
                    <p></p>
                </li>
                <li>
                    <p>2</p>
                    <p></p>
                </li>
                <li>
                    <p>3</p>
                    <p></p>
                </li>
                <li>
                    <p>4</p>
                    <p></p>
                </li>
                <li>
                    <p>5</p>
                    <p></p>
                </li>
                <li>
                    <p>6</p>
                    <p></p>
                </li>
                <li>
                    <p>7</p>
                    <p></p>
                </li>
                <li>
                    <p>8</p>
                    <p></p>
                </li>
                <li>
                    <p>9</p>
                    <p></p>
                </li>
                <li>
                    <p>10</p>
                    <p></p>
                </li>
                <li>
                    <p>11</p>
                    <p></p>
                </li>
                <li>
                    <p>12</p>
                    <p></p>
                </li>
                <li>
                    <p>13</p>
                    <p></p>
                </li>
                <li>
                    <p>14</p>
                    <p></p>
                </li>
                <li>
                    <p>15</p>
                    <p></p>
                </li>
                <li>
                    <p>16</p>
                    <p></p>
                </li>
                <li>
                    <p>17</p>
                    <p></p>
                </li>
                <li>
                    <p class="fontcolor pselect">18</p>
                    <p>余56</p>
                </li>
                <li>
                    <p class="fontcolor">19</p>
                    <p>余66</p>
                </li>
                <li>
                    <p class="fontcolor">20</p>
                    <p>余123</p>
                </li>
                <li>
                    <p class="fontcolor">21</p>
                    <p>余178</p>
                </li>
                <li>
                    <p class="fontcolor">22</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">23</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">24</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">25</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">26</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">27</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">28</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">29</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">30</p>
                    <p>余200</p>
                </li>
                <li>
                    <p class="fontcolor">31</p>
                    <p>余200</p>
                </li>
            </ul>
        </section>

        <div class="bottom-btn">
            <div></div>
            <div onclick="location.href='confirmorder.html'">下一步</div>
        </div>
        
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '@/components/Footer.vue';
export default {
    setup(){

    },
    components:{
        Footer
    }

}
</script>

<style>
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
    width: 82vw;
    margin: 0 auto;
    margin-top: 12vw;
}
section .date-box{
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 4.5vw;
    font-weight: 600;
}
section .date-box p{
    color: #000;
}
section .date-box i{
    color: #999;
    user-select: none;
    cursor: pointer;
}
section table{
    width: 100%;
    font-size: 3.8vw;
}
section table tr th{
    text-align: center;
    font-weight: 600;
    line-height: 12vw;
}

section ul{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
}
section ul li{
    width: 14.28%;
    height: 12vw;
    font-weight: 600;
    color: #999;
}
section ul li p:first-child{
    width: 6vw;
    height: 6vw;
    margin: 0 auto;
    border-radius: 3vw;

    display: flex;
    justify-content: center;
    align-items: center;

    font-size: 4.2vw;

    user-select: none;
    cursor: pointer;
}
section ul li p:last-child{
    font-size: 3vw;
    font-weight: 300;
    text-align: center;
    user-select: none;
}

.fontcolor {
    color: #333;
}

.pselect{
    background-color: #FB902B;
    color: #FFF;
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
.bottom-btn div:first-child{
    flex: 2;
}
.bottom-btn div:last-child{
    flex: 1;
    background-color: #117C94;
    text-align: center;
    line-height: 12vw;
    font-size: 5vw;
    color: #FFF;

    user-select: none;
    cursor: pointer;
}
</style>