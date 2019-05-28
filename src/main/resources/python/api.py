from flask import Flask,jsonify,request
from flask_cors import *
from PIL import Image
import numpy as np
import sys
import re
import json
import massSegment


app = Flask(__name__)#创建一个服务，赋值给APP
CORS(app, esources=r'/*', supports_credentials=True)

@app.route('/segment',methods=['post', 'get'])
def segment():

    # 这里url请求采取'xxx?loca=xxx&name=xxxx'
    # imgLoca是图片文件的绝对位置的文件夹
    # name是文件名称，比较重要，后端根据这个去读取结果
    imgLoca = request.form.get('loca')
    name = request.form.get('name')

    res = re.findall(r"[a-zA-Z]:/.+",imgLoca) # 因为发送的位置，前面多了一个斜杠读不出来，所以正则一下
    imgLoca = res[0]

    imgname = re.findall(r'(.+?)\.',str(name))[0] # 处理图片名，去点后缀

    print("filename is : "+str(imgname))
    print("file is at: "+str(imgLoca))

    # 读取文件内容
    imgArray = np.array(Image.open(imgLoca+name))

    print("正在读取文件..."+imgname)

    # 处理图片
    if massSegment.getLoca(imgLoca, imgname, imgArray):
        print("图片处理成功...")
        return jsonify("true")
    else:
        print("图片处理失败...")
        return jsonify("false")


@app.route('/luntan')
def data():
    with open('liuyan.json','r',encoding='utf8') as f:
        data = json.load(f)
    return jsonify(data)


@app.route('/paper')
def getpaper():
    with open('paper.json','r',encoding='utf8') as f:
        data = json.load(f)
    return jsonify(data)
app.run(host='0.0.0.0',port=8808,debug=False)

'''
使用方法：
cmd 转到该文件目录下
python api.py 即可
不要调用第一个处理图片的接口
咩的用，因为我主程序没有上传
如果提示少了什么包
直接pip下载即可
'''