/*******************************************************************************
 *    Copyright 2023  the original author.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/

package com.zyntaxmind.test.reactive.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.zyntaxmind.test.reactive.graphql.model.User;
import com.zyntaxmind.test.reactive.graphql.service.UserService;
import reactor.core.publisher.Mono;

/**
 * @author dush
 * @date Jun 12, 2023
 *
 */
@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @QueryMapping
  public Mono<User> getUser(@Argument("id") int id) { 
    return userService.getUser(id);
  }
  
  @QueryMapping
  public Mono<User> getUserbyEmail(@Argument("email") String email) { 
    return userService.getUserByEmail(email);
  }

}
