import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import networkx as nx
import json
import time

with open('/home/masuda/Doxuments/CMorph/output/data.json') as json_file:
    data = json.load(json_file)

with open("/home/masuda/Doxuments/CMorph/output/serverdata.json") as j_file:
    serverData=json.load(j_file)

# グラフの作成
G = nx.Graph()


num_drones = 3
num_servers = 4

# ノードとエッジの追加
for i in range(num_drones):
    G.add_node(f"Drone{i}")
for i in range(num_servers):
    G.add_node(f"Server{i}")



positions = {"Drone0":(data[0]["currentX"],data[0]["currentY"]),"Drone1":(data[1]["currentX"],data[1]["currentY"]),
             "Drone2":(data[2]["currentX"],data[2]["currentY"]),"Server0":(100,100),
             "Server1":(100,500),"Server2":(500,100),"Server3":(500,500)}


# アニメーションの初期化
plt.ion()
fig, ax = plt.subplots()

# アニメーションのステップ数
for i in range(0, len(data), 3):
    step_data = data[i:i+3]
    G.clear_edges()
    for j in range(num_drones):
        positions[f"Drone{j}"] = (step_data[j]["currentX"],step_data[j]["currentY"])
        # print(positions[f"Drone{j}"])
        G.add_edge(f"Drone{j}", f"Server{step_data[j]["connectedSeverId"]}")
        
    
        
    # pos = nx.spring_layout(G)
    ax.clear()
    nx.draw(G, pos=positions, with_labels=True)
    plt.pause(0.1)

plt.ioff()
plt.show()
