package com.baichang.android.architecture.login.view;

import android.graphics.drawable.Drawable;
import com.baichang.android.architecture.common.IBaseView;

/**
 * Created by iCong on 2017/2/21.
 */

public interface LoginView extends IBaseView {

  void gotoHome();

  void clean();

  void setAvatar(Drawable drawable);
}
