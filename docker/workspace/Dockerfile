FROM ubuntu:20.04

ENV TZ Asia/Tokyo
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update -y && \
    apt-get upgrade -y && \
    apt-get install -y git fish fzf vim curl zip unzip locales-all openjdk-14-jre
RUN curl -s "https://get.sdkman.io" | bash
RUN chmod +x $HOME/.sdkman/bin/sdkman-init.sh
RUN ["/bin/bash", "-c", "source /root/.sdkman/bin/sdkman-init.sh && sdk install sbt"]
RUN chsh -s /usr/bin/fish
RUN curl https://git.io/fisher --create-dirs -sLo ~/.config/fish/functions/fisher.fish
RUN ["/bin/fish", "-c", "fisher install jethrokuan/fzf"]
COPY config.fish /root/.config/fish/
COPY ./.vimrc /root/
WORKDIR /root/workspace
EXPOSE 9000