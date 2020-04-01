# MoveMeNowReborn
Simple stupid plugin

To explain this by the original author himself:


This is a very simple plugin that will move a player whenever he gets kicked, depending on a whitelist or blacklist.
Configuration :


```yaml
mode: blacklist

# List these by the priority you wish to be given
# The higher in the list, the most likely it will be chosen
servers:
  - "lobby"

disconnect-if-in-lobby: true

list-of-words:
  - "ban"
  - "kick"

message-sent:
  - "%kickmsg%"

```

Mode can either be blacklist or whitelist. In blacklist mode, player will always be moved to default server unless his kick message contains one of the words/phrases in list. In whitelist mode, he will always be kicked unless his kick message contains one of the phrases in list. servername is the name of the server to kick to.

Message is the message sent to the player when he switches server. It can be spanned over multiple lines.
 
%kickmsg% will be replaced with the actual message as sent by the server.

The system is updated to have the following sort of system to lazily load balance the players:
![Load Balance Shit](https://i.imgur.com/MY3cpwl.png)
