package com.hb.lib.mvp.lce;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import android.view.View;

import com.hb.lib.mvp.R;


/**
 * Created by haibt3 on 11/21/2016.
 */

public class LceAnimator {

    private static final long DURATION_ANIM = 300;

    private LceAnimator() {}

    public static void showLoading(@NonNull View loadingView, @NonNull View contentView, @NonNull View errorView) {

        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    public static void showView(final View inView, final View outView) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator in = ObjectAnimator.ofFloat(inView, View.ALPHA, 1f);
        ObjectAnimator loadingOut = ObjectAnimator.ofFloat(outView, View.ALPHA, 0f);

        set.playTogether(in, loadingOut);
        set.setDuration(DURATION_ANIM);

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                inView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                outView.setVisibility(View.GONE);
                outView.setAlpha(1f); // For future showLoading calls
            }
        });

        set.start();
    }

    public static void showErrorView(@NonNull final View loadingView, @NonNull final View contentView, final View errorView) {

        contentView.setVisibility(View.GONE);

        AnimatorSet set = new AnimatorSet();
        ObjectAnimator in = ObjectAnimator.ofFloat(errorView, View.ALPHA, 1f);
        ObjectAnimator loadingOut = ObjectAnimator.ofFloat(loadingView, View.ALPHA, 0f);

        set.playTogether(in, loadingOut);
        set.setDuration(DURATION_ANIM);

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                errorView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                loadingView.setVisibility(View.GONE);
                loadingView.setAlpha(1f); // For future showLoading calls
            }
        });

        set.start();
    }

    public static void showContent(@NonNull final View loadingView, @NonNull final View contentView, @NonNull final View errorView) {

        if (contentView.getVisibility() == View.VISIBLE) {
            errorView.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
        } else {

            errorView.setVisibility(View.GONE);

            final Resources resources = loadingView.getResources();
            final int translateInPixels = resources.getDimensionPixelSize(R.dimen.lce_content_view_animation_translate_y);
            // Not visible yet, so animate the view in
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator contentFadeIn = ObjectAnimator.ofFloat(contentView, View.ALPHA, 0f, 1f);
            ObjectAnimator contentTranslateIn = ObjectAnimator.ofFloat(contentView, View.TRANSLATION_Y,
                    translateInPixels, 0);

            ObjectAnimator loadingFadeOut = ObjectAnimator.ofFloat(loadingView, View.ALPHA, 1f, 0f);
            ObjectAnimator loadingTranslateOut = ObjectAnimator.ofFloat(loadingView, View.TRANSLATION_Y, 0,
                    -translateInPixels);

            set.playTogether(contentFadeIn, contentTranslateIn, loadingFadeOut, loadingTranslateOut);
            set.setDuration(DURATION_ANIM);

            set.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    contentView.setTranslationY(0);
                    loadingView.setTranslationY(0);
                    contentView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    loadingView.setVisibility(View.GONE);
                    loadingView.setAlpha(1f); // For future showLoading calls
                    contentView.setTranslationY(0);
                    loadingView.setTranslationY(0);
                }
            });

            set.start();
        }
    }
}
