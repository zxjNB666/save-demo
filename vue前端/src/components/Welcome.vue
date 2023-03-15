<template>
  <div id="welcome">
    <el-breadcrumb separator="/" style="padding-left:10px;padding-bottom:10px;font-size:12px;">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>欢迎</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="15">
      <!-- 左边部分 -->
      <el-col :span="13">
        <!-- 用户信息表格 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>用户信息</span>
          </div>
          <el-tooltip class="item" effect="dark" content="头像好看吧" placement="top-start">
            <el-avatar
              shape="square"
              :size="90"
              :src="userInfo.avatar"
              style="float:left;"
              :key="1"
            ></el-avatar>
          </el-tooltip>
          <div class="right" style="float:right;width:520px;">
            <el-table :data="tableInfo" border height="120">
              <el-table-column prop="username" label="用户账号"></el-table-column>
              <el-table-column prop="nickname" label="用户昵称"></el-table-column>
              <el-table-column prop="department" label="所属部门"></el-table-column>
              <el-table-column fixed="right" prop="roles" label="用户角色" width="150"></el-table-column>
            </el-table>
          </div>
        </el-card>
        <!-- 功能列表 -->
        <el-row style="margin-top:10px;" :gutter="10">
          <el-card style="height:125px;">
            <el-col :span="6">
              <div class="grid-content bg-purple">
                <router-link to="/products">
                  <img
                    src="../assets/1.svg"
                    alt
                    width="60.8px;"
                    style="margin:0px auto; cursor: pointer;margin-left:20px;"
                  />
                </router-link>
                <div style="font-size:12px;margin-top:5px;margin-left:25px;">物资资料</div>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="grid-content bg-purple-light">
                <router-link to="/stocks">
                  <img
                    src="../assets/2.svg"
                    alt
                    width="60.8px;"
                    style="cursor: pointer;margin-left:20px;"
                  />
                </router-link>
                <div style="font-size:12px;margin-top:5px;margin-left:25px;">物资库存</div>
              </div>
            </el-col>

            <el-col :span="6">
              <div class="grid-content bg-purple-light">
                <router-link to="/inStocks">
                  <img
                    src="../assets/3.svg"
                    alt
                    width="60.8px;"
                    style="cursor: pointer;margin-left:20px;"
                  />
                </router-link>

                <div style="font-size:12px;margin-top:5px;margin-left:25px;">物资入库</div>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="grid-content bg-purple"></div>
              <router-link to="/outStocks">
                <img
                  src="../assets/4.svg"
                  alt
                  width="60.8px;"
                  style="cursor: pointer;margin-left:20px;"
                />
              </router-link>
              <div style="font-size:12px;margin-top:5px;margin-left:25px;">物资发放</div>
            </el-col>
          </el-card>
        </el-row>
        <el-card class="box-card" style="margin-top:20px;padding:0px;">
          <!-- 用户登入报表 -->
          <div id="loginReport" style="width: 650px;height:270px;"></div>
        </el-card>
      </el-col>
      <!-- 右边部分 -->
      <el-col :span="11">
        <div class="grid-content bg-purple">
          <el-card style="min-height:650px;">
            <el-carousel height="310px" interval="5000">
         <el-carousel-item>
        <img src="https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/2.gif" class="carousel_image_type">
      </el-carousel-item>
      <el-carousel-item>
        <img src="https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/1.gif" class="carousel_image_type">
      </el-carousel-item>
      <el-carousel-item>
        <img src="https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/3.gif" class="carousel_image_type">
      </el-carousel-item>

            </el-carousel>
            <aplayer
              style="margin-top:20px;margin-bottom:30px;"
              autoplay
              :music="{
                  title: '出征',
                  artist: '宝石Gem',
                  src: 'http://music.163.com/song/media/outer/url?id=1428110367.mp3',
                  pic: 'https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/lo.png'
              }"
              :list="musicList"
            ></aplayer>

          </el-card>
          <!-- <el-calendar :v-model="new Date()"></el-calendar> -->
        </div>
      </el-col>
    </el-row>

    <!-- <el-card class="box-card">
           <el-calendar v-model="value"></el-calendar>
    </el-card>-->
  </div>
</template>
<script>
import echarts from "echarts";
import aplayer from "vue-aplayer";
export default {
  components: {
    //别忘了引入组件
    aplayer: aplayer
  },

  data() {
    return {
      xData: [],
      yData: [],
      myData: [],
      value: new Date(),
      userInfo: {},
      tableInfo: [],
      musicList:[{
        "artist": "宝石Gem",
        "lrc": "",
        "title": "出征",
        "src": "http://music.163.com/song/media/outer/url?id=1428110367.mp3",
        "pic": "https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/lo.png"
      },{
        "artist": "周典奥",
        "lrc": "",
        "title": "逆行",
        "src": "http://music.163.com/song/media/outer/url?id=1425220190.mp3",
        "pic": "https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/lo1.png"
      }, {
        "artist": "阿冗",
        "lrc": "",
        "title": "你的答案",
        "src": "http://music.163.com/song/media/outer/url?id=1400256289.mp3",
        "pic": "https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/lo2.png"
      }, {
        "artist": "MappleZS",
        "lrc": "",
        "title": "钢铁洪流进行曲-钢琴独奏版",
        "src": "http://music.163.com/song/media/outer/url?id=1396260740.mp3",
        "pic": "https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/lo3.png"
      }, {
        "artist": "Ludovico Einaudi",
        "lrc": "",
        "title": "Nuvole Bianche",
        "src": "http://music.163.com/song/media/outer/url?id=28465109.mp3",
        "pic": "https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/lo4.png"
      }, {
        "artist": "李克勤",
        "lrc": "",
        "title": "月半小夜曲",
        "src": "http://music.163.com/song/media/outer/url?id=115162.mp3",
        "pic": "https://travel-class.oss-cn-hangzhou.aliyuncs.com/images/logo/lo5.png"
      }]
    };
  },
  methods: {

    // async loadMusicList() {
    //   const { data: res } = await this.$http.get(
    //     "music/getPlayList?listId=629987891"
    //   );
    //   if(res.code===200) {
    //     this.musicList = res;
    //   }else {
    //
    //   }
    //
    // },
    /**
     * 加载登入报表数据
     */
    async loginReport(username) {
      const { data: res } = await this.$http.post("loginLog/loginReport", {
        username: username
      });
      if (res.code !== 200) {
        return this.$message.error("获取登入报表数据失败:" + res.msg);
      } else {
        var $this = this;
        this.xData = [];
        this.yData = [];
        this.myData = [];
        res.data.all.forEach(e1 => {
          $this.xData.push(e1.days);
          $this.yData.push(e1.count);
        });

        for (var i = 0; i < this.xData.length; i++) {
          var count = 0;
          for (var j = 0; j < res.data.me.length; j++) {
            if ($this.xData[i] === res.data.me[j].days) {
              count = res.data.me[j].count;
              break;
            } else {
              count = 0;
            }
          }
          $this.myData.push(count);
        }
      }
      this.draw();
    },

    /**
     * 绘制登入报表
     */
    draw() {
      var myChart = echarts.init(document.getElementById("loginReport"));
      // 指定图表的配置项和数据
      var option = {
        title: {
          text: "用户登入统计"
        },
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: "none"
            },
            dataView: { readOnly: false }, //  缩放
            magicType: { type: ["bar", "line"] }, ///　　折线  直方图切换
            restore: {}, // 重置
            saveAsImage: {} // 导出图片
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#eee'
            }
          }
        },
        legend: {
          type:'plain',
          data: ["全部", "我"]
        },
        xAxis: {
          data: this.xData
        },
        yAxis: {
          type: "value"
        },
        series: [
          {
            name: "全部",
            data: this.yData,
            type: "bar",
            showBackground: true,
            animationDuration: 1500,
            animationEasing: "cubicInOut",
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(30, 144, 255，0.5)'
            },
          },
          {
            name: "我",
            data: this.myData,
            type: "bar",
            showBackground: true,
            animationDuration: 2000,
            animationEasing: "cubicInOut"
          }
        ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    }
  },

  created() {
    this.userInfo = this.$store.state.userInfo;
    var roles = this.userInfo.isAdmin ? "超级管理员" : this.userInfo.roles;
    this.tableInfo.push({
      username: this.userInfo.username,
      nickname: this.userInfo.nickname,
      department: this.userInfo.department,
      roles: roles
    });
     this.loadMusicList();
  },
  mounted: function() {
    this.loginReport(this.userInfo.username);
    this.draw();
  },

};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
  
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
  
}
.carousel_image_type{
     width: 100%;
     height: 100%;
  }

</style>
