package com.zjw.apporder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zjw.tablayout.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tabNames = ArrayList<String>()
        var fragments = ArrayList<MyFragemnt>()
        var strList = arrayListOf(
            "资料",
            "动态",
            "相册"
        )
        strList.forEach {
            tabNames.add(it)
            fragments.add(MyFragemnt())
        }
        val adapter = PageAdapter(supportFragmentManager, tabNames, fragments)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = fragments.size

        tabLayout.tabMode = TabLayout.MODE_FIXED
//        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

//        tabLayout.tabGravity = TabLayout.GRAVITY_CENTER
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        //设置tablayout 切换tab的动画
        tabLayout.isNeedSwitchAnimation = true
        //设置tablayout固定线宽 (设置的线宽大于最小tab宽度时 设置线宽失效 用默认tabLayout线宽显示逻辑)
//        tabLayout.selectedTabIndicatorWidth = dpToPx(10)
//        判断tabLayout线宽是否为默认tabLayout线宽显示逻辑 (可用于判断设置固定线宽是否失效)
        //Log.e("Tag","isDefaultIndicatorWidth "+ tabLayout.isDefaultIndicatorWidth)

        //设置tablayout 线宽为包裹内容 (与设置tablayout固定线宽 互斥 所以尽量使用一个,包裹内容优先级高于设置指定线宽)
        tabLayout.setIndicatorWidthWrapContent(true)
        //还原成原来的tablayout默认线宽 (与设置tablayout固定线宽和包裹内容 互斥 所以尽量使用一个，在不指定固定线宽和包裹内容情况下为tablayout默认线宽)
        //tabLayout.selectedTabIndicatorWidth = -1

        tabLayout.setupWithViewPager(viewPager)

        //指示器不需要显示PageAdapter 中的标题所以禁掉

        for (index in 0 until tabLayout.tabCount) {
            //依次获取标签
            val tab = tabLayout.getTabAt(index)
            if (index == 1) {
                tab?.setCustomView(R.layout.design_layout_tab_text1)
            } else {
                tab?.setCustomView(R.layout.design_layout_tab_text2)

            }
            //为每个标签设置自定义布局(如果设置了自定义view 原来系统默认的ImageView和TextView 为gone)
//            tab?.setCustomView(R.layout.item_tab)
        }

//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            var mCurrentPosition: Int = -1
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//                Log.e("Test", "onTabUnselected position == " + tab.position)
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {
//            }
//
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                Log.e("Test", "onTabSelected position == " + tab.position)
//                val textView: TextView = tab.customView!!.findViewById(android.R.id.text1)
//                if (mCurrentPosition == tab.position) {
//                    textView.setCompoundDrawables(null, null, null, null)
//                } else {
//                    mCurrentPosition = tab.position
//                    textView.setCompoundDrawables(null, null, ContextCompat.getDrawable(textView.context, R.drawable.ic_checkbox), null)
//                }
//            }
//
//        })
    }

    internal fun dpToPx(dps: Int): Int {
        return Math.round(resources.displayMetrics.density * dps)
    }
}
