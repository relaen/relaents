
import {DeviceModel} from './devicemodel'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_device_type",'tunnel')
export class DeviceType extends BaseEntity{
	@Id()
	@Column({
		name:'device_type_id',
		type:'int',
		nullable:false
	})
	deviceTypeId:number;

	@Column({
		name:'device_type_name',
		type:'string',
		nullable:true
	})
	deviceTypeName:string;

	@Column({
		name:'extra_fields',
		type:'string',
		nullable:true
	})
	extraFields:string;

	@OneToMany({entity:'DeviceModel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceType',lazyFetch:true})
	deviceModels:Array<DeviceModel>;

}