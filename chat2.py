import matplotlib.pyplot as plt
from matplotlib.widgets import Slider
import numpy as np

# グラフの初期データ生成
x = np.linspace(0, 10, 100)
y = np.sin(x)

# プロットの初期化
fig, ax = plt.subplots()
plt.subplots_adjust(bottom=0.25)  # スライダーのための余白を確保

# グラフを描画
line, = ax.plot(x, y)

# スライダーの軸の位置とサイズ
ax_slider = plt.axes([0.2, 0.02, 0.65, 0.03])

# スライダーの初期値と範囲
slider = Slider(ax_slider, 'Time', 0, 10, valinit=0)

# スライダーの値が変更されたときのイベントハンドラ
def update(val):
    time = slider.val  # スライダーの値を取得
    # 新しいデータを生成
    y_new = np.sin(x + time)
    line.set_ydata(y_new)
    fig.canvas.draw_idle()  # グラフを再描画

# スライダーの値が変更されたときに呼び出すイベントハンドラを設定
slider.on_changed(update)

plt.show()
