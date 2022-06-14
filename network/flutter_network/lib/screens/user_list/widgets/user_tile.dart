import 'package:flutter/material.dart';
import 'package:flutter_network/models/user.dart';
import 'package:flutter_network/screens/user_details/user_details_screen';

class UserTile extends StatelessWidget {
  const UserTile({Key? key, required this.user}) : super(key: key);

  final User user;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context, 
          MaterialPageRoute(builder: (context) => UserDetailsScreen(user: user))
        );
      },
      child: Row(
        children: [
          Image.network(user.avatar),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(user.firstName + ' ' + user.lastName),
                Text(user.email),
                //   )
                const Text("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"), 
              ],
            )
          )
        ]
      )
    );
  }
}