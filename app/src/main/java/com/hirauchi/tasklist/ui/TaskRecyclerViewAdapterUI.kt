package com.hirauchi.tasklist.ui

import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import com.hirauchi.tasklist.R

class TaskRecyclerViewAdapterUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            cardElevation = dip(3).toFloat()
            lparams(width = matchParent, height = wrapContent) {
                horizontalMargin = dip(14)
                verticalMargin = dip(4)
            }

            linearLayout {
                lparams(width = matchParent, height = wrapContent) { padding = dip(8) }

                view {
                    id = R.id.TaskColorLine
                }.lparams(width = dip(6), height = matchParent) {
                    rightMargin = dip(10)
                }

                verticalLayout {
                    linearLayout {
                        textView {
                            id = R.id.TaskImportnace
                        }.lparams {
                            rightMargin = dip(30)
                        }

                        textView {
                            id = R.id.TaskDeadline
                        }
                    }.lparams(width = matchParent, height = dip(20))

                    view {
                        setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.darker_gray))
                    }.lparams(width = matchParent, height = dip(1)) {
                        bottomMargin = dip(3)
                    }

                    textView {
                        id = R.id.TaskContent
                        textSize = 18F
                    }.lparams(width = matchParent, height = wrapContent)
                }.lparams(width = dip(0), height = wrapContent, weight = 1F) {
                    rightMargin = dip(10)
                }

                verticalLayout {
                    relativeLayout {
                        id = R.id.TaskDelete
                        imageView {
                            setImageResource(R.drawable.ic_delete)
                        }.lparams(width = dip(25), height = dip(25)) {
                            centerInParent()
                        }
                    }.lparams(width = matchParent, height = 0, weight = 1F)

                    view().lparams(width = matchParent, height = 0, weight = .5F)

                    relativeLayout {
                        id = R.id.TaskEdit
                        imageView {
                            setImageResource(R.drawable.ic_edit)
                        }.lparams(width = dip(25), height = dip(25)) {
                            centerInParent()
                        }
                    }.lparams(width = matchParent, height = 0, weight = 1F)
                }.lparams(width = dip(30), height = matchParent)
            }
        }
    }
}