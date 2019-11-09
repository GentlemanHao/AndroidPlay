package com.lbxtech.androidplay.bean

class HomeData(val curPage: Int, val offset: Int, val over: Boolean, val pageCount: Int, val size: Int, val total: Int, val datas: List<Article>)

class Article(val id: Int, val title: String, val link: String, val author: String, val chapterId: Int, val chapterName: String, val niceDate: String, val niceShareDate: String)