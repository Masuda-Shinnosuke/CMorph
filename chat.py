import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import json

# JSONデータの読み込み
with open('/home/masuda/Doxuments/CMorph/output/serverdata.json') as json_file:
    data = json.load(json_file)


fig, ax = plt.subplots()
lines = [ax.plot([], [], label=f'Server {i}')[0] for i in range(4)]

# グラフの初期設定
# グラフの初期化
fig, ax = plt.subplots()
lines = [ax.plot([], [], label=f'Server {i}')[0] for i in range(4)]

# グラフの初期設定
def init():
    ax.set_xlim(0, len(data)//128)
    ax.set_ylim(-0.1, 1)
    ax.set_xlabel('Step')
    ax.set_ylabel('currentRho')
    ax.legend()
    return lines

# フレームごとの更新処理
def update(frame):
    # データの更新
    server_data = data[frame * 4: (frame + 1) * 4]
    x_data = list(range(frame, frame + 4))
    y_data = [[item['currentRho']] for item in server_data]

    # プロットの更新
    for i, line in enumerate(lines):
        line.set_data(x_data, y_data[i])
    return lines

# アニメーションの作成
ani = FuncAnimation(fig, update, frames=len(data)//4, init_func=init, blit=True)

# アニメーションの表示
plt.show()