<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <h1>体检预约-登录</h1>
        <section>

            <el-radio-group v-model="radio">
                <div style="display: flex;">
                    <el-radio :label="1"
                        border>密码登录</el-radio>
                    <el-radio :label="2"
                        border>短信验证码登录</el-radio>
                </div>
            </el-radio-group>
            <div class="input-box">
                <i class="fa fa-user-o"></i>
                <input type="text"
                    v-model="users.userId"
                    placeholder="输入手机号">
            </div>
            <div class="input-box">
                <i class="fa fa-lock"></i>
                <input type="password"
                    v-model="users.password"
                    v-if="radio == 1"
                    placeholder="输入登录密码">

                <div style="display: flex;"
                    v-if="radio == 2">
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
            </div>
            <div class="reg-box">
                <p @click="toRegister">注册</p>
                <p>忘记密码？</p>
            </div>
            <div class="button-box"
                @click="login">登录</div>
        </section>

        <footer>
            <div>
                <div class="line"></div>
                <p>有疑问请联系客服</p>
                <div class="line"></div>
            </div>
            <p>4008-XXX-XXX</p>
        </footer>
    </div>
</template>

<script>
//导入需要的基本工具
import { reactive, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import { setSessionStorage } from '@/common';
import axios from 'axios'
import { ElMessage } from 'element-plus';
axios.defaults.baseURL = 'http://localhost:8080/tijian'



export default {
    setup() {
        //声明需要的数据变量
        const router = useRouter();
        const state = reactive({
            users: {
                userId: '',
                password: '',
                // birthday: '',
                // identityCard: '',
                // realName: '',
                // sex: 1,
                // userType: 1
            },
            radio: 1,//选择登录方式
            //短信登录需要的数据
            note: {
                code: '',//用户输入的验证码
                times: 0,//发送短信的次数
                second: 0, //再发送需要等待的秒数
                waitSecond: 60,//默认每次需要等待60s
                timer: null,//计时器
            }
        });

        function login() {
            //用户id的非空校验
            if (state.users.userId == '') {
                ElMessage({
                    type: 'error',
                    message: '手机号码不能为空'
                })
                return;
            }
            //查看用户id是否存在
            if (state.users.userId) {
                axios.post('user/user-exists', state.users)
                    .then(res => {
                        if (res.data == 'NO') {
                            //用户不存在
                            ElMessage({
                                type: 'error',
                                message: '不存在该用户，请先注册'
                            })
                            return;
                        } else {
                            //用户存在
                            if (state.radio == 1) {
                                //密码登录
                                if (state.users.password == '') {
                                    ElMessage({
                                        type: 'error',
                                        message: '密码不能为空'
                                    })
                                    return;
                                }
                                passwordlogin();
                            } else {
                                //短信验证码登录
                                if (state.note.code == '') {
                                    ElMessage({
                                        type: 'error',
                                        message: '验证码不能为空'
                                    })
                                    return;
                                }
                                codeLogin();
                            }
                        }
                    })
                    .catch(err => {
                        return;
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
                axios.post('user/user-exists', state.users)
                    .then(res => {
                        if (res.data == 'NO') {
                            //用户不存在
                            ElMessage({
                                type: 'error',
                                message: '不存在该用户，请先注册'
                            })
                            return;
                        } else {
                            //用户存在
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
                    })
                    .catch(err => {
                        return;
                    })
            }

        }
        //验证码登录
        function codeLogin() {
            //数据的非空校验
            // if (state.users.userId == '') {
            //     ElMessage({
            //         type: 'error',
            //         message: '手机号码不能为空'
            //     })
            //     return;
            // }
            // if (state.note.code == '') {
            //     ElMessage({
            //         type: 'error',
            //         message: '验证码不能为空'
            //     })
            //     return;
            // }

            if (state.note.times > 0) {
                //发送过短信
                let obj = {
                    phoneNumber: state.users.userId,
                    code: state.note.code
                }
                axios.post('user/loginWithCode', obj)
                    .then(res => {
                        let u = res.data;

                        if (u != '') {
                            setSessionStorage('users', u);
                            router.push('/index');
                        } else {
                            ElMessage({
                                type: 'error',
                                message: '手机号或者密码错误'
                            })
                        }
                    })
                    .catch(err => {
                        ElMessage({
                            type: 'error',
                            message: '登录失败'
                        })
                    })
            } else {
                //没有发送过短信
                ElMessage({
                    type: 'error',
                    message: '未发送短信'
                })
            }
        }
        //定义需要的函数
        function passwordlogin() {
            //console.log(state.users.userId + "," + state.users.password)

            //1.数据的非空校验
            // if (state.users.userId == '') {
            //     alert('手机号码不能为空');
            //     return;
            // }

            // if (state.users.password == '') {
            //     alert('密码不能为空');
            //     return;
            // }
            //console.log(state.users)

            //2.访问服务端接口，获取用户信息
            axios.post('user/login', state.users)
                .then((response) => {
                    //拿到响应数据之后
                    // console.log(response.data)
                    let u = response.data;

                    if (u != '') {
                        //console.log('111111111111' + u);
                        //放入浏览器端的session数据存储域
                        setSessionStorage('users', u);
                        router.push('/index');
                        ElMessage({
                            type: 'success',
                            message: '登录成功'
                        })
                    } else {
                        ElMessage({
                            type: 'error',
                            message: '手机号或者密码错误'
                        })
                    }


                }).catch((error) => {
                    //出错之后
                    console.log(error)
                });



        }

        function toRegister() {
            router.push('/register')
        }



        //把数据和函数暴露出去，不然，html访问不到
        return {
            ...toRefs(state),
            login,
            toRegister,
            sendMessage
        }

    }



}
</script>



<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
    width: 100%;
    height: 100%;
    background-image: linear-gradient(45deg, #266C9F, #266C9F, #7EB059);
    overflow: hidden;
}

/*********************** 标题部分 ***********************/
h1 {
    text-align: center;
    color: #FFF;
    font-size: 9.5vw;
    font-weight: 500;
    margin-top: 13vh;
    margin-bottom: 3vh;
}

/*********************** 内容部分 ***********************/
section {
    width: 86vw;
    margin: 0 auto;
    background-color: #FFF;
    border-radius: 2.4vw;

    box-sizing: border-box;
    padding: 6vw;
}

section .input-box {
    width: 100%;
    height: 12vw;
    border: solid 1px #CCC;
    border-radius: 2vw;
    margin-top: 5vw;

    display: flex;
    align-items: center;
}

section .input-box i {
    margin: 0 3.6vw;
    font-size: 5.4vw;
    color: #CCC;
}

section .input-box input {
    border: none;
    outline: none;
}

section .reg-box {
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin-top: 5vw;
    margin-bottom: 2vw;
}

section .reg-box p {
    font-size: 3.3vw;
    color: #2D727E;
    user-select: none;
    cursor: pointer;
}

section .button-box {
    width: 100%;
    height: 13vw;
    border-radius: 2vw;
    background-color: #70B0BC;

    text-align: center;
    line-height: 13vw;
    font-size: 4.6vw;
    color: #FFF;
    letter-spacing: 1vw;

    user-select: none;
    cursor: pointer;
}

/*********************** footer部分 ***********************/
footer {
    width: 86vw;
    margin: 0 auto;
    margin-top: 12vw;
    font-size: 3.8vw;
    color: #FFF;
}

footer div {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

footer div .line {
    width: 22vw;
    height: 1px;
    background-color: #FFF;
}

footer>p {
    text-align: center;
    margin-top: 2vw;
}
</style>