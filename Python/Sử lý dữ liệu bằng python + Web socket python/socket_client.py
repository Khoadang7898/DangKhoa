import socketio

# standard Python
sio = socketio.Client()
# asyncio
sio.connect('http://localhost:00')


@sio.on('chay')
async def on_message(data):
        print('I received a message!')