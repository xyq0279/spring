package day17;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Demo1 {
	public static void main(String[] args) {
		//创建一个窗口
		JFrame frame = new JFrame();
		//设置窗口大小
		frame.setSize(300, 250);
		//设置窗口位置
		frame.setLocation(400, 200);
		//窗口中创建一个按钮
		JButton btn = new JButton("按钮");
		btn.setSize(50, 25);
		btn.setLocation(30, 15);
		/**
		 * 需求：当按钮被点击时，控制台打印一个句话
		 * 事件源：按钮
		 * 事件：点击
		 * 事件发生后实现的功能
		 */
		//监听器
		ActionListener Listener = new ActionListener() {
			//当当前监听器被激活后所执行的方法
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(new Date(e.getWhen()).toLocaleString());
				System.out.println(e.getActionCommand());
				System.out.println(e.getID()+e.paramString());
				System.out.println("呵呵呵呵");
			}
		};
		//需要将监听器注册在指定的按钮上
		btn.addActionListener(Listener);
		
		//将按钮添加到窗口中
		frame.add(btn);
		//设置窗口显示
		frame.setVisible(true);
	}
}
