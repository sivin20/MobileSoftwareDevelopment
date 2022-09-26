import React, { useState, useEffect } from "react";
import { FlatList, Text, View, StyleSheet, Image, TouchableOpacity, SafeAreaView } from "react-native";

export default function MoviesList({navigation}) {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    const resp = await fetch("https://api.themoviedb.org/3/movie/popular?api_key=aeb288a4571b8ddc0f7a60cc616d7512&language=en-US&page=1");
    const data = await resp.json();
    setData(data.results);
  };

  useEffect(() => {
    fetchData();
  }, []);

  function renderItem({item}) {
    return (
      <TouchableOpacity onPress={() => navigation.navigate('Details', 
        {
          id: item.id, 
          title: item.title,
          release_date: item.release_date,
          poster_path: item.poster_path,
          overview: item.overview,
          vote_average: item.vote_average,
        })}>
        <View style={styles.container}>
          <Image
              style={styles.img}
              source={{
                uri: 'https://image.tmdb.org/t/p/w260_and_h390_bestv2/' + item.poster_path,
              }}
            />
          <View style={styles.textContainer}>
            <Text style={styles.title}>{item.title}</Text>
            <Text style={styles.date}>{item.release_date}</Text>
            <Text numberOfLines={2} style={styles.overview}>{item.overview}</Text>
          </View>
        </View>
      </TouchableOpacity>
    );
  }

  return (
    <SafeAreaView>
      <FlatList
        data={data}
        renderItem={renderItem}
        keyExtractor={(item) => item.id}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: "#fff",
    border: "2px solid #FFF",
    borderRadius: 10,
    overflow: "hidden",
    margin: 10,
    display: "flex",
    flex: 2,
    flexDirection: "row",
    boxShadow: "rgba(0, 0, 0, 0.35) 0px 5px 15px",
    maxWidth: 600,
  },
  title: {
    fontSize: 16,
    marginBottom: 10,
    fontWeight: "bold",
    color: "#0671B7",
  },
  img: {
    width: 96,
    height: 140,
  },
  textContainer: {
    marginLeft: 10,
    justifyContent: "center",
    width: "100%"
  },
  date: {
    fontSize: 12,
    color: "#F675A8",
    marginBottom: 10,
  },
  overview: {
    width: "70%",
  }
});