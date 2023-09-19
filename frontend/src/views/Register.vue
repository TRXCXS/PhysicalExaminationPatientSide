<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left"
                onclick="history.go(-1)"></i>
            <p>注册</p>
            <div></div>
        </header>
        <div class="top-ban"></div>
        <h1>欢迎注册</h1>
        <table>
            <tr>
                <td>手机号码</td>
                <td><input type="text"
                        v-model="users.userId"
                        placeholder="请输入手机号码"
                        @blur="isExists"></td>
                <!-- <td><button>发送验证码</button></td> -->
            </tr>
            <tr>
                <td>真实姓名</td>
                <td><input type="text"
                        v-model="users.realName"
                        placeholder="真实姓名便于查看体检报告"></td>
            </tr>
            <tr>
                <td>生日</td>
                <td><input type="date"
                        v-model="users.birthday"></td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <input type="radio"
                        value="1"
                        v-model="users.sex">男
                    <input type="radio"
                        value="0"
                        v-model="users.sex">女
                </td>
            </tr>
            <tr>
                <td>身份证号</td>
                <td><input type="text"
                        placeholder="请输入身份证号"
                        v-model="users.identityCard"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password"
                        placeholder="请输入密码"
                        v-model="users.password"></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input type="password"
                        placeholder="请再次输入密码"
                        v-model="confirmPassword"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td>
                    <div style="display: flex;"
                        >
                        <input v-model="note.code"
                            style="width: 50%;"
                            placeholder="输入短信验证码">
                        <el-button color="#70B0BC"
                            style="color:#FFF"
                            :disabled="note.second > 0"
                            @click="sendMessage">
                            {{ note.second > 0 ? ('请等待' + note.second + '秒') : '发送验证码' }}
                        </el-button>
                    </div>
                </td>
            </tr>
        </table>
        <div class="btn"
            @click="register">完成</div>
    </div>
</template>

<script>
//导入需要的基本工具
import { reactive, registerRuntimeCompiler, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ElMessage } from 'element-plus';
axios.defaults.baseURL = 'http://localhost:8080/tijian'

// private String userId;
// 	private String password;
// 	private String realName;
// 	private Integer sex;
// 	private String identityCard;
// 	private String birthday;
// 	private Integer userType;

export default {
    setup() {
        //声明需要的数据变量
        const router = useRouter();
        const state = reactive({
            users: {
                userId: '',
                password: '',
                realName: '',
                sex: '1',
                identityCard: '',
                birthday: '',
                userType: '1',
            },
            note: {
                code: '',//用户输入的验证码
                times: 0,//发送短信的次数
                second: 0, //再发送需要等待的秒数
                waitSecond: 60,//默认每次需要等待60s
                timer: null,//计时器
            },
            confirmPassword: ''
        });

        //定义需要的函数
        function isExists() {
            //console.log(state.users.userId)
            axios.post('user/user-exists', state.users)
                .then((response) => {
                    //拿到响应数据之后
                    let u = response.data;
                    if (u == 'YES') {
                        alert('手机号已经注册');
                        state.users.userId = '';
                    }
                }).catch((error) => {
                    //出错之后
                    console.log(error)
                });
        }

        function register() {
            //进行数据校验
            if (state.users.userId == '') {
                alert('手机号码不能为空');
                return;
            }
            if (state.users.password == '') {
                alert('密码不能为空');
                return;
            }
            if (state.users.identityCard == '') {
                alert('身份证不能为空');
                return;
            }
            if (state.users.birthday == '') {
                alert('生日不能为空');
                return;
            }
            if (state.users.realName == '') {
                alert('真实姓名不能为空');
                return;
            }
            if (state.users.password != state.confirmPassword) {
                alert('两次密码输入不一致');
                return;
            }

            if (state.note.times > 0) {
                //请求服务端接口插入数据到用户表
                let obj = {
                    user: state.users,
                    code: state.note.code
                }
                axios.post('user/register', obj)
                    .then((response) => {
                        //拿到响应数据之后
                        // console.log(response.data)
                        let u = response.data;

                        if (u == 'Success') {
                            alert('注册成功');
                            //跳转到登录页面登录
                            router.push('/login');
                        } else {
                            alert('注册失败');
                        }
                    }).catch((error) => {
                        //出错之后
                        console.log(error)
                    });
            } else {
                //没有发送过短信
                ElMessage({
                    type: 'error',
                    message: '未发送短信'
                })
            }
        }

        //发送短信
        function sendMessage() {
            //非空校验
            if (state.users.userId == '') {
                ElMessage({
                    type: 'error',
                    message: '手机号码不能为空'
                })
                return;
            } else {
                //发送短信
                axios.get('user/sendCode?phoneNumber=' + state.users.userId)
                    .then(res => {
                        ElMessage({
                            type: 'success',
                            message: '发送短信成功,请耐心等待'
                        })
                    })
                    .catch(err => {
                        ElMessage({
                            type: 'error',
                            message: '发送短信失败,请稍后再试'
                        })
                        console.log(err)
                    })
                //开启倒计时    
                state.note.timer && clearInterval(state.note.timer)
                state.note.second = state.note.waitSecond;
                state.note.timer = setInterval(() => {
                    //console.log(state.note.second)
                    if (state.note.second > 0) {
                        state.note.second--;
                    } else {
                        state.note.second = 0;
                        state.note.timer && clearInterval(state.note.timer)
                    }
                }, 1000)
                state.note.times++;
            }

        }
        //把数据和函数暴露出去，不然，html访问不到
        return {
            ...toRefs(state),
            isExists,
            register,
            sendMessage,
        }

    }



}
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
    width: 100%;
    height: 100%;
}

/*********************** header ***********************/
header {
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

header .fa {
    font-size: 8vw;
}

/*********************** 标题部分 ***********************/
h1 {
    font-size: 7.4vw;
    font-weight: 500;
    box-sizing: border-box;
    padding: 0 3.6vw;
    margin-top: 6vw;
}

/*********************** common样式 ***********************/
.top-ban {
    width: 100%;
    height: 15.7vw;
}

/*********************** table ***********************/
table {
    width: 92.8vw;
    margin: 0 auto;
    margin-top: 5vw;
    border-collapse: collapse;

    font-size: 4.2vw;
}

table tr td {
    height: 12vw;
    border-bottom: solid 1px #DDD;
}

table tr td input {
    border: none;
    outline: none;
}

/*********************** btn ***********************/
.btn {
    width: 92.8vw;
    margin: 0 auto;
    height: 12vw;
    margin-top: 8vw;
    background-color: #137E92;
    border-radius: 2vw;
    color: #FFF;
    font-size: 5vw;
    text-align: center;
    line-height: 12vw;

    user-select: none;
    cursor: pointer;
}
</style>