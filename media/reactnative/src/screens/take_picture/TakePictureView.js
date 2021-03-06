import React, { useEffect, useRef, useState } from 'react';
import { Camera } from 'expo-camera';
import * as MediaLibrary from 'expo-media-library'
import { Button, Text, View, StyleSheet } from 'react-native';
import { PICTURE_DETAILS_ROUTE } from '../../navigation/ROUTES';

const TakePictureView = ({navigation}) => {
  const cameraRef = useRef()
  const [hasCameraPermission, setHasCameraPermission] = useState(undefined)
  const [hasMediaLibraryPermission, setHasMediaLibraryPermission] = useState(undefined)
  const [photo, setPhoto] = useState()

  useEffect(() => {
    (async () => {
      const cameraPermission = await Camera.requestCameraPermissionsAsync();
      const mediaLibraryPermission = await MediaLibrary.requestPermissionsAsync();
      setHasCameraPermission(cameraPermission.status === "granted")
      setHasMediaLibraryPermission(mediaLibraryPermission.status === "granted")
    })()
  }, [])

  if (hasCameraPermission === undefined) {
    return <Text>Requesting camera permissions..</Text>
  } else if (!hasCameraPermission){
    return <Text>Permission for camera not granted.</Text>
  }

  const takePicture = async () => {
    const options = {
      quality: 1,
      base64: true,
      exif: false
    }

    const newPhoto = await cameraRef.current.takePictureAsync(options);

    const asset = await MediaLibrary.createAssetAsync(newPhoto.uri);

    MediaLibrary.createAlbumAsync('Expo', asset)
      .then(() => {
        console.log('Album created!');
      })
      .catch(error => {
        console.log('err', error);
      });

    navigation.navigate(PICTURE_DETAILS_ROUTE, { uri: "data:image/jpg;base64," + newPhoto.base64 })
  }

  return (
    <Camera style={styles.container} ref={cameraRef}>
      <View style={styles.btnContainer}>
        <Button title="Take Picture" onPress={takePicture}/>
      </View>
    </Camera>
  );
}

export default TakePictureView;

const styles = StyleSheet.create({
  container: {
    height: '100%',
    justifyContent: 'flex-end'
  },
  btnContainer: {
    backgroundColor: '#fff',
    alignSelf: 'center',
    marginBottom: 40
  }
})
