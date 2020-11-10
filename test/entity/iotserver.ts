
import {Tunnel} from './tunnel'
import {DeviceModel} from './devicemodel'
import {Device} from './device'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_iot_server",'tunnel')
export class IotServer extends BaseEntity{
	@Id()
	@Column({
		name:'iot_server_id',
		type:'int',
		nullable:false
	})
	iotServerId:number;

	@ManyToOne({entity:IotServer,lazyFetch:true})
	@JoinColumn({name:'tunnel_id',refName:'tunnel_id'})
	tunnel:Tunnel;

	@ManyToOne({entity:IotServer,lazyFetch:true})
	@JoinColumn({name:'device_model_id',refName:'device_model_id'})
	deviceModel:DeviceModel;

	@Column({
		name:'server_no',
		type:'string',
		nullable:true
	})
	serverNo:string;

	@OneToMany({entity:'Device',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'iotServer',lazyFetch:true})
	devices:Array<Device>;

}