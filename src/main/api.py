from flask import Flask,jsonify,request

import sys
sys.path.append("E:\Pycharm\pro2019\projext\BreastSegment")
import massSegment

app = Flask(__name__)#创建一个服务，赋值给APP
@app.route('/test',methods=['post'])
def test():#-----这里的函数名称可以任意取

    info = massSegment.get_random_image()

    return  jsonify(info)#把字典转成json串返回

app.run(host='0.0.0.0',port=8808,debug=False)

