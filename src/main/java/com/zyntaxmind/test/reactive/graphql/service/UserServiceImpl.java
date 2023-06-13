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

package com.zyntaxmind.test.reactive.graphql.service;

import org.springframework.stereotype.Service;
import com.zyntaxmind.test.reactive.graphql.exception.UserNotFoundError;
import com.zyntaxmind.test.reactive.graphql.exception.UserNotFoundException;
import com.zyntaxmind.test.reactive.graphql.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author dush
 * @date Jun 12, 2023
 *
 */
@Service
public class UserServiceImpl implements UserService {

  private static Flux<User> userList = Flux.just(
          new User(1, "James", "Smith", "JamesSmith@gmail.com"),
          new User(2, "Christopher", "Anderson", "ChristopherAnderson@gmail.com"),
          new User(3, "Ronald", "Clark", "RonaldClark@gmail.com"),
          new User(4, "Mary", "Wright", "MaryWright@gmail.com"),
          new User(5, "Lisa", "Mitchell", "LisaMitchell@gmail.com"),
          new User(6, "Michelle", "Johnson", "MichelleJohnson@gmail.com")
          );


  @Override
  public Mono<User> getUser(int id) {
    return userList.flatMap(user -> {
      if (user.id() == id) {
        return Mono.just(user);
      }
      return Mono.empty();
    })
    .next()
    .switchIfEmpty(
        Mono.error(new UserNotFoundException("Can't find user by id: " + id, UserNotFoundError.USER_NOT_FOUND))
    );
  }
  
  @Override
  public Mono<User> getUserByEmail(String email) {
    return userList.flatMap(user -> {
      if (user.email().equals(email)) {
        return Mono.just(user);
      }
      return Mono.empty();
    })
    .next()
    .switchIfEmpty(
        Mono.error(new UserNotFoundException("Can't find user by email: " + email, UserNotFoundError.EMAIL_NOT_FOUND))
    );
  }

}
