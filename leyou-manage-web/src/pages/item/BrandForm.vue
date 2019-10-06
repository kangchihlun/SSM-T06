<template>
  <v-form v-model="valid" ref="myBrandForm">
    <v-text-field v-model="brand.name" label="请输入品牌名称" required :rules="nameRules"/>
    <v-text-field v-model="brand.letter" label="请输入品牌首字母" required :rules="letterRules"/>
    <v-cascader
      url="/item/category/list"
      multiple
      required
      v-model="brand.categories"
      label="请选择商品分类"/>
    <v-layout row>
      <v-flex xs3>
        <span style="font-size: 16px; color: #444">品牌LOGO：</span>
      </v-flex>
      <v-flex>
        <v-upload
          v-model="brand.image" url="/upload/image" :multiple="false" :pic-width="250" :pic-height="90"
        />
      </v-flex>
    </v-layout>
    <v-layout class="my-4" row>
      <v-spacer/>
      <v-btn @click="submit" color="primary">提交</v-btn>
      <v-btn @click="clear">重置</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
  export default {
    name: "brand-form",
    props: {
      oldBrand: {
        type: Object
      },
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        valid: false, // 表单校验结果标记
        brand: {
          name: '', // 品牌名称
          letter: '', // 品牌首字母
          image: '',// 品牌logo
          categories: [], // 品牌所属的商品分类数组
        },
        nameRules: [
          // !v 代表一個bool  !再取反
          v => !!v || "品牌名称不能为空",
          v => v.length > 1 || "品牌名称至少2位"
        ],
        letterRules: [
          v => !!v || "首字母不能为空",
          v => /^[a-zA-Z]{1}$/.test(v) || "品牌字母只能是1个字母"
        ]
      }
    },
    methods: {
      submit() {
        // 表单校验
        // 參閱Line2 把myBrandForm 加入Vue.$refs
        // if(this.$refs.myBrandForm.validate())
        if (this.valid) {  
          // 定义一个请求参数对象，通过解构表达式来获取brand中的属性，剩下param組成一個對象
          const {categories, letter, ...params} = this.brand;
          // 数据库中只要保存分类的id即可，因此我们对categories的值进行处理,只保留id，并转为字符串
          params.cids = categories.map(c => c.id).join(","); // 把陣列轉成逗號分隔的字符串
          console.log(params.cids)
          ///console.log(categories)
          // params.cid2 = categories.map(c=>{
          //   console.log(c)
          // })

          // 将字母都处理为大写
          params.letter = letter.toUpperCase();
          // 将数据提交到后台
          // this.$http.post('/item/brand', this.$qs.stringify(params))
          // qs.stringify 轉化對象=>用 & 串起來的字串
          this.$http({
            method: this.isEdit ? 'put' : 'post',
            url: '/item/brand',
            // 配合後臺不能沒有這種類的定義，改成用$qs，參閱 main.js Line17
            //data: params
            data: this.$qs.stringify(params)
          }).then(() => {
            // 关闭窗口
            this.$emit("close");
            this.$message.success("保存成功！");
          })
            .catch(() => {
              this.$message.error("保存失败！");
            });
        }
      },
      clear() {
        // 重置表单
        ///this.$refs.myBrandForm.reset();
        // 需要手动清空商品分类
        ///this.categories = [];
      }
    },
    watch: {
      oldBrand: {// 监控oldBrand的变化
        handler(val) {
          if (val) {
            // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
            this.brand = Object.deepCopy(val)
          } else {
            // 为空，初始化brand
            this.brand = {
              name: '',
              letter: '',
              image: '',
              categories: [],
            }
          }
        },
        deep: true
      }
    }
  }
</script>

<style scoped>

</style>
