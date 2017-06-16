/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * This class can then be used to wrap up operations that while in progress should block tests from
 * accessing the UI.
 */
public final class SimpleCountingIdlingResource {

  private final String mResourceName;

  private final AtomicInteger counter = new AtomicInteger(0);

  // written from main thread, read from any thread.

  /**
   * Creates a SimpleCountingIdlingResource
   *
   * @param resourceName the resource name this resource should report to Espresso.
   */
  public SimpleCountingIdlingResource(String resourceName) {
    mResourceName = resourceName;
  }

  /**
   * Increments the count of in-flight transactions to the resource being monitored.
   */
  public void increment() {
    counter.getAndIncrement();
  }

  /**
   * Decrements the count of in-flight transactions to the resource being monitored.
   *
   * If this operation results in the counter falling below 0 - an exception is raised.
   *
   * @throws IllegalStateException if the counter is below 0.
   */
  public void decrement() {
    int counterVal = counter.decrementAndGet();
    if (counterVal < 0) {
      throw new IllegalArgumentException("Counter has been corrupted!");
    }
  }
}
