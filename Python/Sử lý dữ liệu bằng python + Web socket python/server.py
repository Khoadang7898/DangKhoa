from aiohttp import web
import socketio
import pandas as pd
sio = socketio.AsyncServer()
app = web.Application()
sio.attach(app)
import os
from werkzeug.utils import secure_filename






@sio.on('message')
async  def print_messi(sid, message):
            print('Socket: ', sid)
            await sio.emit("chay","data")

@sio.on('hinh2')
async  def print_mesi(sid, message):
            print('Socket: ', sid)
            await sio.emit("hinhh2","data")


@sio.on('print-server')
async def print_server(sid,message):
    print(message)
    await sio.emit('print_qt',message)

@sio.on('hinh1')
async def print_server(sid,message):
    print(message)
    await sio.emit('hinhh1',message)
@sio.on('print-server-ts')
async def print_server_ts(sid,message):
    print(message)
    await sio.emit('print_ts',message)
@sio.on('print-server-kq')
async def print_server_kq(sid,message):
    print(message)
    await sio.emit('print_kq',message)
@sio.on('connect')
def concected(sid, message):
    print("connected")

async def index(request):
    with open('templates/index.html') as f:
        return web.Response(text=f.read(), content_type='text/html')
app.router.add_static('/static', 'static')

app.router.add_get('/', index)
async def handler( request):
       reader = await request.multipart()
       image = await reader.next()
       filename = image.filename
       print (filename) # this printed file name
       size = 0
       with open(os.path.join('DataSet/', filename), 'wb') as f:
           while True:
               chunk = await image.read_chunk()
               if not chunk:
                   break
               size += len(chunk)
               f.write(chunk)
       with open('templates/index.html') as f:
        return web.Response(text=f.read(), content_type='text/html')
app.router.add_post('/upload-file',handler)
if __name__ == '__main__':
    web.run_app(app)