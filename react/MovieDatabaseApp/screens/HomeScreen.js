import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {NavigationContainer, StackActions} from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

export default function HomeScreen({navigation}) {
  return (
      <View style={styles.container}>
        <Text style={styles.h3}>Welcome to:</Text>
        <Text style={styles.h1}>What To Watch?</Text>
        <Text style={styles.h5}>The Movie Database which helps you decide what to watch tonight!</Text>
        <Button
          color="#F8B7CD"
          title="Enter"
          onPress={() => navigation.navigate('Movies')}
        />
      </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#C8E7F5',
    alignItems: 'center',
    justifyContent: 'center',
  },
  h1: {
    fontSize: 34,
    color: "#0671B7",
    marginTop: 10,
  },
  h3: {
    fontSize: 26,
    color: "#67A3D9",
  },
  h5: {
    textAlign: "center",
    fontSize: 20,
    margin: 20,
    color: "#000",
  },
});
